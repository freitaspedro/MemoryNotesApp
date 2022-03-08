package com.example.core.repository

import com.example.core.data.Note

class NoteRepository(private val dataSource: NoteDataSource) {
    suspend fun add(note: Note) = dataSource.add(note)
    suspend fun get(id: Long) = dataSource.get(id)
    suspend fun getAll() = dataSource.getAll()
    suspend fun delete(note: Note) = dataSource.delete(note)
}