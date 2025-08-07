package com.akzuza.indi

import androidx.compose.runtime.*
import com.akzuza.indi.common.databaseBuilderModule
import com.akzuza.indi.modules.daosModule
import com.akzuza.indi.modules.repositoriesModule
import com.akzuza.indi.modules.viewModelsModule
import com.akzuza.indi.viewmodels.AppViewModel
import com.akzuza.indi.viewmodels.HomeViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinMultiplatformApplication
import org.koin.compose.koinInject
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.KoinConfiguration

@OptIn(KoinExperimentalAPI::class)
@Composable
@Preview
fun App() {
    KoinMultiplatformApplication (
        config = KoinConfiguration {
            modules(
                viewModelsModule,
                repositoriesModule,
                databaseBuilderModule(),
                daosModule
            )
        }
    ) {
        val appViewModel = koinInject<AppViewModel>()
        val homeViewModel = koinInject<HomeViewModel>()

        Indi(
            viewModel = appViewModel,
            homeViewModel = homeViewModel
        )
    }
}