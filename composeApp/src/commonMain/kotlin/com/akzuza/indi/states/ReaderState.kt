package com.akzuza.indi.states

import androidx.compose.ui.graphics.ImageBitmap
import com.akzuza.indi.data.Title

data class ReaderState(
    val title: Title = Title(title = ""),
    val bitmaps: List<ImageBitmap> = emptyList()
)
