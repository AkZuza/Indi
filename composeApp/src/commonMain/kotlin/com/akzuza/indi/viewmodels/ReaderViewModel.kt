package com.akzuza.indi.viewmodels

import androidx.lifecycle.ViewModel
import com.akzuza.indi.repositories.TitleRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

// Does not maintain state with activity
class ReaderViewModel: ViewModel(), KoinComponent {

    private val titleRepository: TitleRepository by inject()

}