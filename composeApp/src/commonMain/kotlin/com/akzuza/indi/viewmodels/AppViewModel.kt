package com.akzuza.indi.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.akzuza.indi.data.NavRoutes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class AppViewModel : ViewModel() {

    private val _backstack = MutableStateFlow<List<NavRoutes>>(listOf(NavRoutes.HomeScreen))
    val backstack: StateFlow<List<NavRoutes>> = _backstack

    fun navigateTo(route: NavRoutes) {
        _backstack.update {
            it + route
        }
    }

    fun goBack() {
        _backstack.update {
            val mut = it.toMutableList()
            mut.removeLastOrNull()
            mut
        }
    }

}