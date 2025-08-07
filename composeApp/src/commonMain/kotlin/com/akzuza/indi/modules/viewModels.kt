package com.akzuza.indi.modules

import com.akzuza.indi.repositories.LocalTitleRepository
import com.akzuza.indi.repositories.TitleRepository
import com.akzuza.indi.viewmodels.AppViewModel
import com.akzuza.indi.viewmodels.HomeViewModel
import com.akzuza.indi.viewmodels.ReaderViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val homeViewModelModule = module {
    viewModelOf(::HomeViewModel)
}


val appViewModelModule = module {
    viewModelOf(::AppViewModel)
}

val readerViewModel = module {
    viewModelOf(::ReaderViewModel)
}

val viewModelsModule = module {
    includes(appViewModelModule, homeViewModelModule, readerViewModel)
}