package com.akzuza.indi.common

import android.content.Intent
import android.os.Build
import android.os.CancellationSignal
import android.provider.DocumentsContract
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.net.toFile
import com.akzuza.indi.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.io.File

actual class FilePicker {
    actual companion object {

        @RequiresApi(Build.VERSION_CODES.Q)
        fun init(activity: MainActivity) {
            val contentResolver = activity.contentResolver

            openDoc = activity.registerForActivityResult(
                ActivityResultContracts.OpenDocument()
            ) { uri ->

                // make Uri persistent

                uri?.apply {
                    contentResolver.takePersistableUriPermission(
                        uri, Intent.FLAG_GRANT_READ_URI_PERMISSION
                    )

                    contentResolver.query(uri, arrayOf(DocumentsContract.Document.COLUMN_DISPLAY_NAME), null, null)?.use { cursor ->

                        val index = cursor.getColumnIndex(DocumentsContract.Document.COLUMN_DISPLAY_NAME)
                        cursor.moveToFirst()
                        val filename = cursor.getString(index)
                        val path = uri.toString()

                        singleFileResult = PlatformFile(
                            filename = filename,
                            path = path
                        )
                    }
                }

                isRunning = false
            }
        }

        actual suspend fun getSingleFile(): PlatformFile? = singleFileResult

        actual suspend fun startFilePicker() {
            singleFileResult = null
            openDoc.launch(arrayOf("application/pdf"))
            isRunning = true
            while (isRunning);
        }

        // Results
        private var singleFileResult: PlatformFile? = null
        private var isRunning = false
        private lateinit var activity: MainActivity

        // Contracts
        private lateinit var openDoc: ActivityResultLauncher<Array<String>>

    }
}