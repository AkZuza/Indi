package com.akzuza.indi.screens.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.painterResource
import indi.composeapp.generated.resources.Res
import indi.composeapp.generated.resources.logo
import kotlinx.coroutines.delay
import org.jetbrains.compose.ui.tooling.preview.Preview

/*
Splash Screen to act as a placeholder screen
During this time it will check if a user has already logged in or not
maybe check if it expired
then proceeding from that point it will go to login page or home screen

This is the first screen that appears
 */

@Preview
@Composable
fun Splash(
    navigateToHome: () -> Unit,
    navigateToLogin: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(Res.drawable.logo),
                contentDescription = null,
                modifier = Modifier.scale(2.0f),
                tint = Color.Unspecified
            )
        }

        LaunchedEffect(Unit) {
            // Do some logic to check if there is a user logged in
            // the user can be logged in via google or apple
            // but maybe user does not want an account
            // it should understand the usage of the app without an account
            // but lose out on sync controls

            // navigate to the correct page

            val goToHome = true

            if(goToHome) {
                navigateToHome()
            } else {
                navigateToLogin()
            }
        }
    }
}