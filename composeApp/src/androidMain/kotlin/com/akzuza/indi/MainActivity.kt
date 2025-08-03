package com.akzuza.indi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.akzuza.indi.common.FilePicker
import com.akzuza.indi.viewmodels.AppViewModel
import com.akzuza.indi.viewmodels.HomeViewModel

class MainActivity : ComponentActivity() {

    private val openDocResult = registerForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri ->

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        FilePicker.init(this)

        setContent {

            val appViewModel = viewModel<AppViewModel>()
            val homeViewModel = viewModel<HomeViewModel>()

            Indi(
                viewModel = appViewModel,
                homeViewModel = homeViewModel
            )
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    val appViewModel = viewModel<AppViewModel>()
    val homeViewModel = viewModel<HomeViewModel>()
    Indi(
        viewModel = appViewModel,
        homeViewModel = homeViewModel
    )
}