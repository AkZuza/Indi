package com.akzuza.indi.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akzuza.indi.data.Title
import com.akzuza.indi.repositories.TitleRepository
import com.akzuza.indi.states.ReaderState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

// Does not maintain state with activity
class ReaderViewModel: ViewModel(), KoinComponent {

    private val titleRepository: TitleRepository by inject()
    private val _uiState = MutableStateFlow<ReaderState>(ReaderState())
    val uiState: StateFlow<ReaderState> = _uiState

    fun loadTitle(titleId: Long) {
        viewModelScope.launch {
            val title = titleRepository.getTitleById(titleId)
            if(title == null) return@launch

            // Get the file by its URI
            // Slowly load pages later on

            _uiState.update {
                it.copy(title = title)
            }
        }
    }
}