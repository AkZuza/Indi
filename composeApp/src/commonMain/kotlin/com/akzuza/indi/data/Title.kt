package com.akzuza.indi.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "titles")
data class Title(
    @PrimaryKey val title_id: Long = 0,
    val title: String,
    val total_pages: Long = 0,
    val current_page: Long = 0,
    val latest_page: Long = 0,
    val reading_mode: ReadingMode = ReadingMode.Default,
    val status: TitleStatus = TitleStatus.Reading,
    val uri: String = "",
    val cover_uri: String = "",
    val enable_sync: Boolean = false
)
