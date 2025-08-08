package com.akzuza.indi.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.isSecondaryPressed
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.akzuza.indi.NightSurface
import com.akzuza.indi.data.Title
import com.akzuza.indi.data.TitleStatus

val cardShape = RoundedCornerShape(10.dp)
val deleteColor = Color(0xff881111)
val detailsColor = Color(0xff111188)

@Composable
fun TitleCard(
    title: Title,
    openReader: () -> Unit,
    removeTitle: () -> Unit,
    openDetails: () -> Unit
) {

    val friendlyTitle = title.title.substring(
        0, title.title.indexOfLast { '.' == it }
    )

    OutlinedCard(
        modifier = Modifier.fillMaxSize()
    ) {
        val swipeState = rememberSwipeToDismissBoxState(
            confirmValueChange = {
                if(it == SwipeToDismissBoxValue.StartToEnd) openDetails()
                else if(it == SwipeToDismissBoxValue.EndToStart) removeTitle()

                it != SwipeToDismissBoxValue.StartToEnd
            }
        )

        SwipeToDismissBox (
            modifier = Modifier.fillMaxSize(),
            state = swipeState,
            backgroundContent = {
                when(swipeState.dismissDirection) {
                    SwipeToDismissBoxValue.StartToEnd -> {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = "Details",
                            modifier = Modifier
                                .fillMaxSize()
                                .background(detailsColor)
                                .wrapContentSize(Alignment.CenterStart)
                                .padding(12.dp)
                        )
                    }
                    SwipeToDismissBoxValue.EndToStart -> {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Remove",
                            modifier = Modifier
                                .fillMaxSize()
                                .background(deleteColor)
                                .wrapContentSize(Alignment.CenterEnd)
                                .padding(12.dp)
                        )
                    }
                    SwipeToDismissBoxValue.Settled -> {}
                }
            }
        )
        {
            Column(
                modifier = Modifier.fillMaxSize()
                    .background(NightSurface)
                    .clip(cardShape)
                    .combinedClickable(
                        hapticFeedbackEnabled = true,
                        onClick = {
                            openReader()
                        }
                    )
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    friendlyTitle,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                val titleStatus = when(title.status) {
                    TitleStatus.Reading -> "Reading"
                    TitleStatus.Dropped -> "Dropped"
                    TitleStatus.ReadAgain -> "Read Again"
                    TitleStatus.PlanToRead -> "Planning to read"
                }
                Text("Status: $titleStatus")

                val progress = title.current_page
                Text("Progress: $progress")
            }
        }
    }
}

@Composable
private fun TitleDropDownMenu(
    modifier: Modifier = Modifier,
    expanded: Boolean,
    onDismissReq: () -> Unit,
    removeTitle: () -> Unit,
    openDetails: () -> Unit
) {
    DropdownMenu(
        modifier = modifier,
        expanded = expanded,
        onDismissRequest = onDismissReq
    ) {
        DropdownMenuItem(
            modifier = Modifier.padding(4.dp),
            text = {
                Row (
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Delete, null)
                    Text("Remove")
                }
            },
            onClick = {
                removeTitle()
                onDismissReq()
            }
        )

        DropdownMenuItem(
            modifier = Modifier.padding(4.dp),
            text = {
                Row (
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Info, null)
                    Text("Details")
                }
            },
            onClick = {
                openDetails()
                onDismissReq()
            }
        )
    }
}
