package com.akzuza.indi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.akzuza.indi.common.PlatformNavigation
import com.akzuza.indi.data.NavRoutes
import com.akzuza.indi.screens.home.IndiHome
import com.akzuza.indi.screens.login.LoginScreen
import com.akzuza.indi.screens.reader.ReaderScreen
import com.akzuza.indi.screens.splash.Splash
import com.akzuza.indi.viewmodels.AppViewModel
import com.akzuza.indi.viewmodels.HomeViewModel
import com.akzuza.indi.viewmodels.ReaderViewModel
import org.koin.compose.koinInject
import org.koin.core.context.startKoin

@Composable
fun Indi(
    viewModel: AppViewModel = koinInject(),
    homeViewModel: HomeViewModel = koinInject(),
    readerViewModel: ReaderViewModel = koinInject()
) {

    val backstackState by viewModel.backstack.collectAsStateWithLifecycle()
    val homeState by homeViewModel.uiState.collectAsStateWithLifecycle()
    val readerState by readerViewModel.uiState.collectAsStateWithLifecycle()


    IndiTheme {
        PlatformNavigation(
            backstack = backstackState.toMutableList(),
            splashScreen = {
                Splash(
                    navigateToHome = {
                        viewModel.navigateTo(NavRoutes.HomeScreen)
                    },
                    navigateToLogin = {
                        viewModel.navigateTo(NavRoutes.LoginScreen)
                    }
                )
            },

            loginScreen = {
                LoginScreen(
                    navigatetoHome = {
                        viewModel.navigateTo(NavRoutes.HomeScreen)
                    }
                )
            },

            homeScreen = {
                IndiHome(
                    state = homeState,
                    addTitle = homeViewModel::addTitle,
                    removeTitle = homeViewModel::removeTitle,
                    openReader = { it ->
                        viewModel.navigateTo(NavRoutes.ReaderScreen(it))
                    }
                )
            },

            readerScreen = {
                val route = backstackState.last() as? NavRoutes.ReaderScreen
                if(route != null ) {
                    ReaderScreen(
                        titleId = route.titleId,
                        readerState = readerState,
                        startLoadingTitle = readerViewModel::startLoadingTitle,
                        loadTitle = { readerViewModel.loadTitle(titleId = route.titleId) },
                        updateCurrentPage = readerViewModel::updateTitleCurrentPage
                    )
                }
            },

            onBack = {
                viewModel.goBack()
            }
        )
    }
}