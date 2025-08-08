package com.akzuza.indi.viewmodels

import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akzuza.indi.common.FilePicker
import com.akzuza.indi.data.Title
import com.akzuza.indi.repositories.TitleRepository
import com.akzuza.indi.states.ReaderState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

// Does not maintain state with activity
class ReaderViewModel: ViewModel(), KoinComponent {

    private val titleRepository: TitleRepository by inject()
    private val _uiState = MutableStateFlow(ReaderState())
    val uiState: StateFlow<ReaderState> = _uiState

    private var loading = false

    fun loadTitle(titleId: Long) {
        viewModelScope.launch {
            val title = titleRepository.getTitleById(titleId)
            if(title == null) return@launch

            _uiState.update {
                it.copy(title = title, bitmaps = emptyList())
            }
        }
    }

    fun startLoadingTitle(titleId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            val title = titleRepository.getTitleById(titleId)
            if(title != null) {
                FilePicker.generatePdfBitmaps(title).collect { bitmap ->
                    _uiState.update {
                        it.copy(
                            bitmaps = it.bitmaps + bitmap
                        )
                    }
                }
            }
        }
    }

    fun updateTitleCurrentPage(titleId: Long, currentPage: Long) {
        viewModelScope.launch {
            val title = titleRepository.getTitleById(titleId)
            title?.apply {
                val updatedTitle = title.copy(
                    current_page = currentPage
                )

                titleRepository.updateTitle(updatedTitle)
            }
        }
    }
}