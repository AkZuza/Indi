package com.akzuza.indi.common

import java.awt.Desktop
import java.awt.FileDialog
import java.awt.Frame
import java.io.File
import javax.swing.JFileChooser

actual class FilePicker {
    actual companion object {

        fun init(window: Frame) {
            frame = window
            singleFileDialog = FileDialog(
                frame, "Choose a file", FileDialog.LOAD
            )
        }
        actual suspend fun startFilePicker() {
            if(Desktop.isDesktopSupported()) {
                val homeDir = System.getProperty("user.home")
                singleFileDialog.directory = homeDir
                singleFileDialog.isVisible = true
            }
        }

        actual suspend fun getSingleFile(): PlatformFile? {
            val file = singleFileDialog.file
            val path = singleFileDialog.directory + file

            file?.apply {
                return PlatformFile(
                    file,
                    path
                )
            }
            return null
        }

        private lateinit var frame: Frame
        private val fileChooser = JFileChooser()
        private lateinit var singleFileDialog: FileDialog
    }
}