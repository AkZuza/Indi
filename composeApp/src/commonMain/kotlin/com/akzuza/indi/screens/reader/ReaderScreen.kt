package com.akzuza.indi.screens.reader

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
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
    val loading = readerState.loading
    val pagerState = rememberPagerState(pageCount = { bitmaps.size })

    LaunchedEffect(titleId) {
        loadTitle()
        startLoadingTitle(titleId)
    }

    LaunchedEffect(loading) {
        if(!loading) {
            pagerState.animateScrollToPage(readerState.title.current_page.toInt())
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Box (
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {

            if(loading) {
                CircularProgressIndicator()
            } else {
                PdfReaderPagerView(
                    pagerState = pagerState,
                    bitmaps = bitmaps,
                    updateCurrentPage = { index -> updateCurrentPage(titleId, index) }
                )
            }
        }
    }
}