package com.akzuza.indi.screens.home

import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
        0, title.title.indexOfLast { '.' == it  }
    )

    var showExtraMenu by remember { mutableStateOf(false) }

    Box {
        OutlinedCard (
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
                            if(event.type == PointerEventType.Press && event.buttons.isSecondaryPressed) {
                                showExtraMenu = true
                            }
                        }
                    }
                }
        ) {
            Column (
                modifier = Modifier.fillMaxSize().padding(8.dp)
            ) {
                Text(
                    "$friendlyTitle",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        TitleDropDownMenu(
            showExtraMenu,
            {showExtraMenu = false},
            removeTitle,
            openDetails
        )
    }
}

@Composable
private fun TitleDropDownMenu(
    expanded: Boolean,
    onDismissReq: () -> Unit,
    removeTitle: () -> Unit,
    openDetails: () -> Unit
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissReq
    ) {
        DropdownMenuItem(
            text = { Text("Remove") },
            onClick = {
                removeTitle()
                onDismissReq()
            }
        )

        DropdownMenuItem(
            text = { Text("Details") },
            onClick = {
                openDetails()
                onDismissReq()
            }
        )
    }
}
