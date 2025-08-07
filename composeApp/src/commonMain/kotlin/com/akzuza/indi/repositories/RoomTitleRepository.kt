package com.akzuza.indi.repositories

import com.akzuza.indi.daos.TitleDao
import com.akzuza.indi.data.Title
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RoomTitleRepository (
    private val titleDao: TitleDao
): TitleRepository {

    override suspend fun addTitle(title: Title) = titleDao.add(title)

    override suspend fun removeTitle(title: Title) = titleDao.delete(title)

    override fun getAllTitles(): Flow<List<Title>> = titleDao.getAll()

    override fun getTitle(
        titleId: Long?,
        title: String?
    ): Flow<Title?> = emptyFlow()

    override suspend fun getTitleByName(title: String): Title? = titleDao.getByTitle(title)
    override suspend fun getTitleById(titleId: Long): Title? = titleDao.getOne(titleId)
}