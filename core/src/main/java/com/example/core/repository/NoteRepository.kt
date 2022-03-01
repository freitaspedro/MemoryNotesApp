package com.example.core.repository

class NoteRepository(private val dataSource: NoteDataSource) {
    suspend fun add(note: Note) = dataSource.add(note)
    suspend fun get(id: Long) = dataSource.get(id)
    suspend fun getAll() = dataSource.getAll()
    suspend fun remove(note: Note) = dataSource.remove(note)
}