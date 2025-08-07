package com.akzuza.indi.common

import androidx.room.Room
import com.akzuza.indi.data.AppDatabase
import org.koin.core.module.Module
import org.koin.dsl.module
import java.io.File

actual fun databaseBuilderModule(): Module = module {
    single {
        val dbFile = File(System.getProperty("java.io.tmpdir"), "indi.db")
        Room.databaseBuilder<AppDatabase>(
            dbFile.absolutePath
        ).build()
    }
}