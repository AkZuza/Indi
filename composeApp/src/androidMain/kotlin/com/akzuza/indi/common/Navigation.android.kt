package com.akzuza.indi.common

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.akzuza.indi.data.NavRoutes
import com.akzuza.indi.screens.splash.Splash
import kotlin.math.log

@Composable
actual fun PlatformNavigation(
    splashScreen: @Composable () -> Unit,
    detailsScreen: @Composable () -> Unit,
    homeScreen: @Composable () -> Unit,
    loginScreen: @Composable () -> Unit,
    readerScreen: @Composable () -> Unit,
    settingsScreen: @Composable () -> Unit,
    onBack: () -> Unit,
    backstack: MutableList<NavRoutes>,
) {

    NavDisplay(
        backStack = backstack,
        onBack = { onBack() },
        entryProvider = entryProvider {

            entry<NavRoutes.SplashScreen> {
                splashScreen()
            }

            entry<NavRoutes.HomeScreen> {
                homeScreen()
            }

            entry<NavRoutes.LoginScreen> {
                loginScreen()
            }

            entry<NavRoutes.DetailsScreen> {
                detailsScreen()
            }

            entry<NavRoutes.SettingsScreen> {
                settingsScreen()
            }

            entry<NavRoutes.ReaderScreen> {
                readerScreen()
            }
        }
    )
}

@Composable
actual fun PlatformVerticalScrollBar(modifier: Modifier, state: LazyListState) {
}