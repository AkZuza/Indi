package com.akzuza.indi.common

import android.content.Intent
import android.graphics.pdf.PdfRenderer
import android.net.Uri
import android.os.Build
import android.os.CancellationSignal
import android.os.Parcel
import android.os.ParcelFileDescriptor
import android.provider.DocumentsContract
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.net.toFile
import androidx.core.net.toUri
import com.akzuza.indi.MainActivity
import com.akzuza.indi.data.Title
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.io.File

actual class FilePicker {
    actual companion object {

        @RequiresApi(Build.VERSION_CODES.Q)
        fun init(activity: MainActivity) {
            this.activity = activity
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

        @RequiresApi(Build.VERSION_CODES.Q)
        actual suspend fun analyzeAndFillPdfTitle(title: Title): Title {
            val uri = title.uri
            val contentResolver = activity.contentResolver
            val parcel = contentResolver.openFile(uri.toUri(), "r", null)

            if(parcel == null) return title

            val renderer = createPdfRenderer(parcel)
            val newTitle = title.copy(
                total_pages = renderer?.pageCount?.toLong() ?: 0
            )
            renderer?.close()
            parcel.close()

            return newTitle
        }

        @RequiresApi(Build.VERSION_CODES.Q)
        fun createPdfRenderer(parcel: ParcelFileDescriptor): PdfRenderer? {
            val renderer = PdfRenderer(parcel)
            return renderer
        }

        // Results
        private var singleFileResult: PlatformFile? = null
        private var isRunning = false
        private lateinit var activity: MainActivity

        // Contracts
        private lateinit var openDoc: ActivityResultLauncher<Array<String>>


    }
}