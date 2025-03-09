package com.example.practiceapplication.data

data class RssTile(
    val title: String,
    val content: String,
    val type: BlogType,
    val mediaSrc: Int? = null
)
