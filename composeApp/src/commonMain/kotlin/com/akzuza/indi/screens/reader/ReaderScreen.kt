package com.akzuza.indi.screens.reader

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import com.akzuza.indi.common.FilePicker
import com.akzuza.indi.states.ReaderState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlin.collections.toMutableList

@Composable
fun ReaderScreen(
    titleId: Long,
    readerState: ReaderState,
    startLoadingTitle: (Long) -> Unit,
    loadTitle: () -> Unit,
    updateCurrentPage: (Long, Long) -> Unit
) {
    val bitmaps = readerState.bitmaps
    val startPage = readerState.title.current_page
    val lazyList = rememberLazyListState()
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Column (
            modifier = Modifier.fillMaxSize().padding(paddingValues)
        ) {

            PdfReaderView(
                startPage,
                bitmaps,
                updateCurrentPage = { index -> updateCurrentPage(titleId, index)},
                listState = lazyList
            )
        }
    }


    LaunchedEffect(titleId) {
        loadTitle()
        startLoadingTitle(titleId)
    }

    LaunchedEffect(readerState.bitmaps.size) {
        if(readerState.bitmaps.size >= startPage && lazyList.firstVisibleItemIndex == 0) {
            lazyList.scrollToItem(startPage.toInt())
        }
    }
}