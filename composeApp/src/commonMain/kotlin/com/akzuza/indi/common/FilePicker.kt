package com.akzuza.indi.common

expect class FilePicker {

    companion object {
        suspend fun getSingleFile(): PlatformFile?
        suspend fun startFilePicker()
    }
}