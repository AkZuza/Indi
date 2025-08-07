package com.akzuza.indi.common

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.material.NavigationRail
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.akzuza.indi.data.NavRoutes

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
    val navigationController = rememberNavController()

    NavHost(
        navController = navigationController, startDestination = NavRoutes.HomeScreen
    ) {
        composable<NavRoutes.LoginScreen> {
            loginScreen()
        }

        composable<NavRoutes.HomeScreen> {
            homeScreen()
        }

        composable<NavRoutes.ReaderScreen> { readerScreen() }

        composable<NavRoutes.SettingsScreen> { settingsScreen() }

        composable<NavRoutes.DetailsScreen> { detailsScreen() }
    }
}

@Composable
actual fun PlatformVerticalScrollBar(modifier: Modifier, state: LazyListState) {
    VerticalScrollbar(
        modifier = modifier,
        adapter = rememberScrollbarAdapter(state)
    )
}