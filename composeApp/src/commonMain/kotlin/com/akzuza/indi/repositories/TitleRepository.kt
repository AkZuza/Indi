package com.akzuza.indi.repositories

import com.akzuza.indi.data.Title
import kotlinx.coroutines.flow.Flow

interface TitleRepository {
    suspend fun addTitle(title: Title)
    suspend fun removeTitle(title: Title)
    fun getAllTitles(): Flow<List<Title>>
    fun getTitle(
        titleId: UInt? = null,
        title: String? = null,
    ): Flow<Title?>
}