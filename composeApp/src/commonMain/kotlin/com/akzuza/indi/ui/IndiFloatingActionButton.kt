package com.akzuza.indi.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.akzuza.indi.NightSurface
import com.akzuza.indi.SepiaText
import com.akzuza.indi.SurfaceLight

@Composable
fun IndiFloatingActionButton(
    modifier: Modifier = Modifier,
    darkmode: Boolean =  isSystemInDarkTheme(),
    onClick: () -> Unit,
    content: @Composable () -> Unit,
) {
    FloatingActionButton(
        modifier = modifier,
        content = content,
        onClick = onClick,
        containerColor = if (darkmode) NightSurface else SurfaceLight,
        contentColor = if (darkmode) SepiaText else Color.White
    )
}