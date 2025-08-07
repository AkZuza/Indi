package com.akzuza.indi.screens.reader

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.akzuza.indi.states.ReaderState

@Composable
fun ReaderScreen(
    titleId: Long? = null,
    readerState: ReaderState
) {
    Scaffold(

    ) {
        val title = readerState.title
        Text(title.title)
    }
}