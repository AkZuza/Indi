package com.akzuza.indi.screens.reader

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp

@Composable
fun PdfReaderView(
    startPage: Long,
    bitmaps: List<ImageBitmap>,
    listState: LazyListState,
    updateCurrentPage: (Long) -> Unit) {

    LazyColumn (
        modifier = Modifier.fillMaxSize().background(Color.Black),
        state = listState,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(bitmaps.size) { i ->
            val bitmap = bitmaps[i]
            Image(
                bitmap = bitmap,
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth().background(Color.White),
            )
        }
    }

    LaunchedEffect(listState.firstVisibleItemIndex) {
        updateCurrentPage(listState.firstVisibleItemIndex.toLong())
    }
}