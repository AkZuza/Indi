package com.akzuza.indi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.akzuza.indi.common.PlatformNavigation
import com.akzuza.indi.data.NavRoutes
import com.akzuza.indi.screens.home.IndiHome
import com.akzuza.indi.screens.login.LoginScreen
import com.akzuza.indi.screens.splash.Splash
import com.akzuza.indi.viewmodels.AppViewModel
import com.akzuza.indi.viewmodels.HomeViewModel

@Composable
fun Indi(
    viewModel: AppViewModel,
    homeViewModel: HomeViewModel
) {

    val backstack = viewModel.backstack
    val homeState by homeViewModel.uiState.collectAsStateWithLifecycle()

    IndiTheme {
        PlatformNavigation(
            backstack = backstack,
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
                    removeTitle = homeViewModel::removeTitle
                )
            }
        )
    }
}