package com.akzuza.indi.screens.reader

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import com.akzuza.indi.common.FilePicker
import com.akzuza.indi.data.Title
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlin.math.max
import kotlin.math.min

class PdfReaderState(private val title: Title) {

    val totalPageCount = title.total_pages
    var currentPage = title.current_page
        private set

    fun nextPage(incrementSize: Int = 1) {
        currentPage = max(currentPage + incrementSize, totalPageCount)
    }

    fun previousPage(incrementSize: Int = 1) {
        currentPage = min(currentPage - incrementSize, 0)
    }

    fun setPage(page: Long) {
        if(page > totalPageCount || page < 0) {
            return // no need to execute this
        }
        currentPage = page
    }

    suspend fun startLoading() {
        if(title.uri.isEmpty()) return
        val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
        coroutineScope.launch {
            FilePicker.generatePdfBitmaps(title).collect {
                bitmaps.add(it)
            }
        }
    }
    var bitmaps = emptyList<ImageBitmap>().toMutableList()
}

@Composable
fun rememberPdfReaderState(title: Title): PdfReaderState {
    var state by remember { mutableStateOf(PdfReaderState(title)) }
    return state
}