package com.akzuza.indi.common

import androidx.room.Room
import com.akzuza.indi.data.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun databaseBuilderModule(): Module = module {
    single {
        val context = androidContext()
        val dbFile = context.getDatabasePath("indi.db")
        Room.databaseBuilder<AppDatabase>(
            context,
            dbFile.absolutePath
        ).build()
    }
}