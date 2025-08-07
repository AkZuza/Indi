package com.akzuza.indi.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akzuza.indi.data.Title
import com.akzuza.indi.repositories.LocalTitleRepository
import com.akzuza.indi.repositories.TitleRepository
import com.akzuza.indi.states.IndiHomeState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeViewModel: ViewModel(), KoinComponent {

    private val _titlesRepo: TitleRepository  by inject()

    private var _uiState: MutableStateFlow<IndiHomeState> = MutableStateFlow(IndiHomeState())
    val uiState: StateFlow<IndiHomeState> = _uiState

    init {
        viewModelScope.launch {
            _titlesRepo.getAllTitles().collect { titles ->
                _uiState.update { state ->
                    state.copy(
                        titles = titles
                    )
                }
            }
        }
    }

    fun addTitle(title: Title) {
        viewModelScope.launch {
            if (_titlesRepo.getTitleByName(title.title) == null) {
                _titlesRepo.addTitle(title)
            }
        }
    }

    fun removeTitle(title: Title) {
        viewModelScope.launch {
            _titlesRepo.removeTitle(title)
        }
    }

}