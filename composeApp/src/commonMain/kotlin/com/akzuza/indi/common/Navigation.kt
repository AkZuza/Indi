package com.akzuza.indi.common

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import com.akzuza.indi.data.NavRoutes

@Composable
expect fun PlatformNavigation(
    splashScreen: @Composable () -> Unit = {},
    detailsScreen: @Composable () -> Unit = {},
    homeScreen: @Composable () -> Unit = {},
    loginScreen: @Composable () -> Unit = {},
    readerScreen: @Composable () -> Unit = {},
    settingsScreen: @Composable () -> Unit = {},
    onBack: () -> Unit,
    backstack: MutableList<NavRoutes> = emptyList<NavRoutes>().toMutableStateList(),
)

@Composable
expect fun PlatformVerticalScrollBar(modifier: Modifier = Modifier, state: LazyListState)