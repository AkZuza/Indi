package com.akzuza.indi.data

data class Title(
    val title_id: UInt,
    val title: String,
    val total_pages: UInt = 0U,
    val current_page: UInt = 0U,
    val latest_page: UInt = 0U,
    val reading_mode: ReadingMode = ReadingMode.Default,
    val status: TitleStatus = TitleStatus.Reading,
    val uri: String = "",
    val cover_uri: String = "",
    val enable_sync: Boolean = false
)
