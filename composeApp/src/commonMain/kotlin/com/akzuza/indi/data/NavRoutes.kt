package com.akzuza.indi.data

import kotlinx.serialization.Serializable

sealed interface NavRoutes {
    @Serializable
    object SplashScreen: NavRoutes
    @Serializable
    object HomeScreen: NavRoutes
    @Serializable
    object LoginScreen: NavRoutes
    @Serializable
    object DetailsScreen: NavRoutes
    @Serializable
    object SettingsScreen: NavRoutes
    @Serializable
    object ReaderScreen: NavRoutes
}