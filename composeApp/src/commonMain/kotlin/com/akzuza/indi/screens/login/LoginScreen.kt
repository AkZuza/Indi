package com.akzuza.indi.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akzuza.indi.data.NavRoutes
import com.akzuza.indi.ui.IndiPrimaryText
import com.akzuza.indi.ui.IndiTextButton
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun LoginScreen(
    navigatetoHome: () -> Unit
) {
    Scaffold (
    ) {

        Column (
            modifier = Modifier.fillMaxSize().padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            IndiPrimaryText(
                "Login",
                fontSize = 30.sp,
                fontFamily = FontFamily.Monospace,
            )

            Spacer(modifier = Modifier.height(20.dp))

            IndiTextButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    navigatetoHome()
                },
                text = "Apple"
            )

            IndiTextButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    navigatetoHome()
                },
                text = "Google"
            )

            Spacer(modifier = Modifier.height(10.dp))

            IndiTextButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    navigatetoHome()
                },
                text = "Without an account"
            )
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        navigatetoHome = {

        }
    )
}