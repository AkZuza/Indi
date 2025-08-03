package com.akzuza.indi

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform