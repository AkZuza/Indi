package com.akzuza.indi.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akzuza.indi.common.FilePicker
import com.akzuza.indi.common.PlatformFile
import com.akzuza.indi.common.PlatformVerticalScrollBar
import com.akzuza.indi.data.Title
import com.akzuza.indi.states.IndiHomeState
import com.akzuza.indi.ui.IndiFloatingActionButton
import com.akzuza.indi.ui.IndiSurfaceText
import com.akzuza.indi.ui.IndiTextField
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview

private val searchBarShape = RoundedCornerShape(50.dp)

@Composable
fun IndiHome(
    state: IndiHomeState,
    addTitle: (Title) -> Unit,
    removeTitle: (Title) -> Unit
) {

    val titles = state.titles
    val scope = rememberCoroutineScope()

    Scaffold (
        modifier = Modifier.fillMaxSize(),

        floatingActionButton = {
            IndiFloatingActionButton(
                onClick = {
                    scope.launch(Dispatchers.IO) {
                        FilePicker.startFilePicker()
                        val file = FilePicker.getSingleFile()

                        file?.apply {
                            val title = Title(
                                title_id = 0U,
                                title = file.filename,
                                uri = file.path
                            )

                            addTitle(title)
                        }
                    }
                }
            ) {
                IndiSurfaceText("+", fontSize = 30.sp)
            }
        }
    ) { padding ->

        var searchFilter by remember { mutableStateOf("") }
        val lazyListState = rememberLazyListState()

        Column (
            modifier = Modifier.fillMaxSize().padding(padding).padding(8.dp)
        ) {

            IndiTextField(
                modifier = Modifier.fillMaxWidth(),
                text = searchFilter,
                onUpdate = { searchFilter = it },
                shape = searchBarShape
            )

            Spacer(modifier = Modifier.height(8.dp))

            Box (
                modifier = Modifier.fillMaxSize()
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    state = lazyListState,
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(all = 8.dp)
                ) {
                    items(state.titles.size) { i ->
                        val title = state.titles[i]
                        TitleCard(
                            title,
                            openReader = {

                            },

                            removeTitle = {
                                removeTitle(title)
                            },

                            openDetails = {

                            }
                        )
                    }
                }

                PlatformVerticalScrollBar(modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(), lazyListState)
            }
        }
    }
}