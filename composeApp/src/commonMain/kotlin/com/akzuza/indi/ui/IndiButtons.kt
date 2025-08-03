package com.akzuza.indi.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.akzuza.indi.BlueAccent
import com.akzuza.indi.ButtonDisabled
import com.akzuza.indi.ButtonDisabledDark
import com.akzuza.indi.SepiaText
import com.akzuza.indi.TextDarkBrown

@Composable
fun IndiTextButton(
    modifier: Modifier = Modifier,
    darkTheme: Boolean = isSystemInDarkTheme(),
    enabled: Boolean = true,
    fontSize: TextUnit = 12.sp,
    onClick: () -> Unit,
    text: String
) {
    TextButton(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = BlueAccent,
            contentColor = Color.White,
            disabledContainerColor = if (darkTheme) ButtonDisabledDark else ButtonDisabled,
            disabledContentColor = if (darkTheme) SepiaText else TextDarkBrown
        )
    ) {
        IndiSurfaceText(text = text, fontSize = fontSize)
    }
}