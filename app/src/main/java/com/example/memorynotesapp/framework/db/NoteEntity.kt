package com.example.memorynotesapp.framework.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.data.Note

@Entity(tableName = "note")
data class NoteEntity (
    val title: String,
    val content: String,
    @ColumnInfo(name = "create_at")
    val createAt: Long,
    @ColumnInfo(name = "update_at")
    val updateAt: Long,
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L
) {
    companion object {
        fun fromNote(note: Note) = NoteEntity(note.title, note.content, note.createAt, note.updateAt)
    }
    fun toNote() = Note(title, content, createAt, updateAt, id)
}