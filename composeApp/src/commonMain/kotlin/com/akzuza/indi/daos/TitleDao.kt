package com.akzuza.indi.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.akzuza.indi.data.Title
import kotlinx.coroutines.flow.Flow

@Dao
interface TitleDao {
    @Query("SELECT * FROM titles")
    fun getAll(): Flow<List<Title>>

    @Query("SELECT * FROM titles WHERE title_id = :title_id")
    suspend fun getOne(title_id: Long): Title?

    @Insert
    suspend fun add(title: Title)

    @Delete
    suspend fun delete(title: Title)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(title: Title)
}