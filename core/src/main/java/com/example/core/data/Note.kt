package com.example.core.data

data class Note (
    var title: String,
    var content: String,
    var createAt: Long,
    var updateAt: Long,
    var id: Long = 0,
    var wordCount: Int = 0
)