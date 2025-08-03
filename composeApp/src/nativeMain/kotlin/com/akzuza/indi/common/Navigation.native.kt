package com.akzuza.indi.common

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.akzuza.indi.data.NavRoutes

@androidx.compose.runtime.Composable
actual fun PlatformVerticalScrollBar(modifier: Modifier, state: LazyListState) {
}

@androidx.compose.runtime.Composable
actual fun PlatformNavigation(
    splashScreen: @androidx.compose.runtime.Composable (() -> Unit),
    detailsScreen: @androidx.compose.runtime.Composable (() -> Unit),
    homeScreen: @androidx.compose.runtime.Composable (() -> Unit),
    loginScreen: @androidx.compose.runtime.Composable (() -> Unit),
    readerScreen: @androidx.compose.runtime.Composable (() -> Unit),
    settingsScreen: @androidx.compose.runtime.Composable (() -> Unit),
    backstack: MutableList<NavRoutes>
) {
}