package com.akzuza.indi.screens.reader

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.akzuza.indi.data.Title
import kotlin.math.max
import kotlin.math.min

class PdfReaderState(title: Title) {

    init {
        // Load image data here
    }
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
}

@Composable
fun rememberPdfReaderState(title: Title): PdfReaderState {
    var state by rememberSaveable { mutableStateOf(PdfReaderState(title)) }
    return state
}