package com.akzuza.indi.modules

import com.akzuza.indi.repositories.LocalTitleRepository
import com.akzuza.indi.repositories.RoomTitleRepository
import com.akzuza.indi.repositories.TitleRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val titlesRepositoryModule = module {
    singleOf(::RoomTitleRepository) { bind<TitleRepository>() }
}

val repositoriesModule = module {
    includes(titlesRepositoryModule)
}