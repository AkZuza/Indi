package com.akzuza.indi

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

// Light theme colorScheme
val LightThemeColors = lightColorScheme(
    primary = BlueAccent,
    secondary = GreenAccent,
    background = PaperBackground,
    surface = SurfaceLight,
    onPrimary = Color.White,
    onSecondary = TextDarkBrown,
    onBackground = TextDarkBrown,
    onSurface = TextDarkBrown,
    error = ErrorPink,
    onError = Color.White
)

// Dark theme colorScheme
val DarkThemeColors = darkColorScheme(
    primary = BlueAccent,
    secondary = GreenAccent,
    background = NightBackground,
    surface = NightSurface,
    onPrimary = Color.White,
    onSecondary = SepiaText,
    onBackground = SepiaText,
    onSurface = SepiaText,
    error = ErrorPink,
    onError = NightBackground
)