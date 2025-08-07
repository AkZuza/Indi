package com.akzuza.indi.modules

import com.akzuza.indi.daos.TitleDao
import com.akzuza.indi.data.AppDatabase
import org.koin.compose.koinInject
import org.koin.dsl.module

val titleDao = module {
    single<TitleDao> {
        val db = get<AppDatabase>()
        val dao = db.titleDao()
        dao
    }
}

val daosModule = module {
    includes(titleDao)
}