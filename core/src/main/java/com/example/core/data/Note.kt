package com.example.core.data

data class Note {
    var title: String,
    var content: String,
    var creationTiem: Long,
    var updateTime: Long,
    var id: Long = 0
}