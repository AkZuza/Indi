package com.akzuza.indi.ui

import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun IndiPrimaryText(
    text: String,
    fontSize: TextUnit = 12.sp,
    fontFamily: FontFamily? = null,
) {
    Text(
        text = text,
        fontSize = fontSize,
        fontFamily = fontFamily,
        color = MaterialTheme.colorScheme.onBackground,
        style = MaterialTheme.typography.bodyLarge // or another style
    )
}

@Composable
fun IndiSurfaceText(
    text: String,
    fontSize: TextUnit = 12.sp,
    fontFamily: FontFamily? = null,
) {
    Text(
        text = text,
        fontSize = fontSize,
        fontFamily = fontFamily,
        color = MaterialTheme.colorScheme.onSurface,
        style = MaterialTheme.typography.bodyMedium
    )
}