package com.akzuza.indi.screens.home

import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.isSecondaryPressed
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.akzuza.indi.data.Title

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

    var showExtraMenu by remember { mutableStateOf(false) }

    OutlinedCard(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .combinedClickable(
                    onLongClick = {
                        showExtraMenu = true
                    },
                    hapticFeedbackEnabled = true,
                    onClick = {
                        openReader()
                    }
                )
                .pointerInput(
                    Unit
                ) {
                    awaitPointerEventScope {
                        while (true) {
                            val event = awaitPointerEvent()
                            if (event.type == PointerEventType.Press && event.buttons.isSecondaryPressed) {
                                showExtraMenu = true
                            }
                        }
                    }
                }
                .padding(12.dp)
        ) {
            Row {
                Text(
                    friendlyTitle,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Box (
                    modifier = Modifier.width(20.dp)
                ) {
                    Spacer(modifier = Modifier.width(20.dp))

                    TitleDropDownMenu(
                        modifier = Modifier.align(Alignment.BottomStart),
                        expanded = showExtraMenu,
                        onDismissReq = {showExtraMenu = false},
                        removeTitle = removeTitle,
                        openDetails = openDetails
                    )
                }
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
