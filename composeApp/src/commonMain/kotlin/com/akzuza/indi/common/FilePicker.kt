package com.akzuza.indi.common

import androidx.compose.ui.graphics.ImageBitmap
import com.akzuza.indi.data.Title
import kotlinx.coroutines.flow.Flow

expect class FilePicker {

    companion object {
        suspend fun getSingleFile(): PlatformFile?
        suspend fun startFilePicker()
        suspend fun analyzeAndFillPdfTitle(title: Title): Title
    }
}