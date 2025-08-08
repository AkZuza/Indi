package com.akzuza.indi.common

import android.content.ContentResolver
import android.content.Intent
import android.graphics.Bitmap
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
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.core.net.toFile
import androidx.core.net.toUri
import com.akzuza.indi.MainActivity
import com.akzuza.indi.data.Title
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.File
import kotlin.coroutines.coroutineContext
import androidx.core.graphics.createBitmap

actual class FilePicker {
    actual companion object {

        @RequiresApi(Build.VERSION_CODES.Q)
        fun init(activity: MainActivity) {
            this.activity = activity
            contentResolver = activity.contentResolver

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

        private val pdfReaderScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

        @RequiresApi(Build.VERSION_CODES.Q)
        actual fun generatePdfBitmaps(title: Title): Flow<ImageBitmap>  {
            val contentResolver = contentResolver
            return flow {
                val totalCount = title.total_pages
                val uri = title.uri
                val parcel = contentResolver.openFile(uri.toUri(), "r", null)

                if(parcel != null ){
                    val renderer = createPdfRenderer(parcel)
                    for (i in (0 until totalCount)) {
                        val page = renderer?.openPage(i.toInt())
                        if (page != null) {
                            val bitmap = createBitmap(page.width*2, page.height*2)
                            page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_PRINT)

                            val imageBitmap: ImageBitmap = bitmap.asImageBitmap()
                            emit(imageBitmap)
                            page.close()
                        }
                    }
                    renderer?.close()
                    parcel.close()
                }
            }
        }


        // Results
        private var singleFileResult: PlatformFile? = null
        private var isRunning = false
        private lateinit var activity: MainActivity
        private lateinit var contentResolver: ContentResolver

        // Contracts
        private lateinit var openDoc: ActivityResultLauncher<Array<String>>

    }
}