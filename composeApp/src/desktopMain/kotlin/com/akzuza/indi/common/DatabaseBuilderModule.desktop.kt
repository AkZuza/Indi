package com.akzuza.indi.common

import androidx.room.Room
import androidx.sqlite.SQLiteDriver
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.akzuza.indi.data.AppDatabase
import org.koin.core.module.Module
import org.koin.dsl.module
import java.io.File

actual fun databaseBuilderModule(): Module = module {
    single<AppDatabase> {
        val dbFile = File(System.getProperty("java.io.tmpdir"), "indi.db")
        Room.databaseBuilder<AppDatabase>(
            dbFile.absolutePath
        ).setDriver(BundledSQLiteDriver()).build()
    }
}