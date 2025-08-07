package com.akzuza.indi.repositories

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import com.akzuza.indi.data.Title
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update

class LocalTitleRepository : TitleRepository {

    private var _titles = MutableStateFlow<List<Title>>(emptyList())
    private val titles: StateFlow<List<Title>> = _titles

    override suspend fun addTitle(title: Title) {
        _titles.update { titles ->
            val temp = titles.toMutableList()
            temp.add(title)
            temp
        }
    }

    override suspend fun removeTitle(title: Title) {
        _titles.update { titles ->
            val temp = titles.toMutableList()
            temp.remove(title)
            temp
        }
    }

    override fun getAllTitles(): Flow<List<Title>> = titles

    override fun getTitle(
        titleId: Long?,
        title: String?
    ): Flow<Title?> = flow {
        emit (if(titleId != null) titles.value.find { titleId == it.title_id }
        else if(title != null) titles.value.find { title == it.title }
        else null)
    }

    override suspend fun getTitleByName(title: String): Title? {
        return null
    }

}