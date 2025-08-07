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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import kotlin.math.exp

private val searchBarShape = RoundedCornerShape(15.dp)

@OptIn(ExperimentalMaterial3Api::class)
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
        var expanded by remember { mutableStateOf(false) }
        val lazyListState = rememberLazyListState()

        Column (
            modifier = Modifier.fillMaxSize().padding(padding).padding(8.dp)
        ) {

            DockedSearchBar(
                inputField = {
                    TextField(
                        value = searchFilter,
                        onValueChange = {
                            searchFilter = it
                            if (searchFilter.isNotEmpty() && !expanded) {
                                expanded = true
                            }
                        },
                        placeholder = {
                            Text("Search")
                        },
                        trailingIcon = {
                            if (searchFilter.isNotEmpty()) {
                                IconButton(
                                    onClick = {
                                        searchFilter = ""
                                        expanded = false
                                    }
                                ) {
                                    Icon(Icons.Default.Clear, contentDescription = null)
                                }
                            }
                        },
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                expanded = expanded,
                onExpandedChange = { e: Boolean ->
                    expanded = e
                },
                shape = searchBarShape,
                tonalElevation = 4f.dp,
                shadowElevation = 8f.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                val filteredTitles = state.titles.filter { title -> title.title.contains(searchFilter) }
                if(searchFilter.isNotEmpty() && filteredTitles.isNotEmpty()) {
                    Box (
                        modifier = Modifier.fillMaxSize()
                    ) {
                        LazyColumn(
                            modifier = Modifier.fillMaxWidth(),
                            state = lazyListState,
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            contentPadding = PaddingValues(all = 8.dp)
                        ) {

                            items(filteredTitles.size) { i ->
                                val title = filteredTitles[i]

                            }
                        }

                        PlatformVerticalScrollBar(modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(), lazyListState)
                    }
                } else {
                    ListItem(
                        headlineContent = { Text("No title found for query $searchFilter")}
                    )
                }
            }

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