package com.akzuza.indi.screens.reader

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp

@Composable
fun PdfReaderPagerView(
    pagerState: PagerState,
    bitmaps: List<ImageBitmap>,
    updateCurrentPage: (Long) -> Unit
) {

    VerticalPager(
        modifier = Modifier.fillMaxSize(),
        state = pagerState,
        pageSpacing = 8.dp,
        beyondViewportPageCount = 2
    ) {
        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier.fillMaxSize()
                    .background(Color.White),
                bitmap = bitmaps[it],
                contentDescription = null
            )
        }
    }

    LaunchedEffect(pagerState) {
        snapshotFlow { updateCurrentPage(pagerState.currentPage.toLong()) }.collect {
        }
    }
}