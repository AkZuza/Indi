package com.akzuza.indi

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.akzuza.indi.common.FilePicker
import com.akzuza.indi.viewmodels.AppViewModel
import com.akzuza.indi.viewmodels.HomeViewModel

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Indi",
    ) {
        FilePicker.init(this.window)

        App()
    }
}