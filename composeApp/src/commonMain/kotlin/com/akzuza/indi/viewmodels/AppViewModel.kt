package com.akzuza.indi.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.akzuza.indi.data.NavRoutes

class AppViewModel : ViewModel() {

    private var _backstack: MutableList<NavRoutes> = mutableStateListOf(NavRoutes.HomeScreen)
    val backstack: MutableList<NavRoutes> = _backstack

    fun navigateTo(route: NavRoutes) {
        _backstack.removeLastOrNull()
        _backstack.add(route)
    }
}