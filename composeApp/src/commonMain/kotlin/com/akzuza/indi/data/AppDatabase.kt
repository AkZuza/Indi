package com.akzuza.indi.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.akzuza.indi.daos.TitleDao
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

@Database(
    entities = [Title::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun titleDao(): TitleDao
}

