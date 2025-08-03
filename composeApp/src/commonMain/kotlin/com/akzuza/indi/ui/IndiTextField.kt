package com.akzuza.indi.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import org.jetbrains.compose.ui.tooling.preview.Preview
import com.akzuza.indi.*


@Composable
private fun readerTextFieldColors(darkTheme: Boolean = isSystemInDarkTheme()): TextFieldColors {
    return TextFieldDefaults.colors(
        focusedTextColor = if (darkTheme) SepiaText else TextDarkBrown,
        disabledTextColor = if (darkTheme) Color(0xFF6A6A6A) else Color(0xFFA3A3A3),
        focusedContainerColor = if (darkTheme) NightSurface else SurfaceLight,
        cursorColor = BlueAccent,
        errorCursorColor = ErrorPink,
        focusedLabelColor = BlueAccent,
        unfocusedLabelColor = if (darkTheme) SepiaText else TextDarkBrown,
        errorLabelColor = ErrorPink,
        focusedPlaceholderColor = if (darkTheme) SepiaText.copy(alpha = 0.6f) else TextDarkBrown.copy(alpha = 0.5f),
        disabledPlaceholderColor = if (darkTheme) Color(0xFF6A6A6A) else Color(0xFFA3A3A3),
    )
}

@Preview
@Composable
fun IndiTextField(
    modifier: Modifier = Modifier,
    text: String,
    onUpdate: (String) -> Unit,
    shape: Shape = OutlinedTextFieldDefaults.shape,
    singleLine: Boolean = true,
) {
    OutlinedTextField(
        modifier = modifier,
        value = text,
        onValueChange = onUpdate,
        shape = shape,
        colors = readerTextFieldColors(),
        singleLine = singleLine
    )
}