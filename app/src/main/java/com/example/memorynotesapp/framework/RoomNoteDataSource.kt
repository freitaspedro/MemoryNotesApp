package com.example.memorynotesapp.framework

import android.content.Context
import com.example.core.data.Note
import com.example.core.repository.NoteDataSource
import com.example.memorynotesapp.framework.db.DatabaseService
import com.example.memorynotesapp.framework.db.NoteEntity

class RoomNoteDataSource(context: Context) : NoteDataSource {
    private val noteDao = DatabaseService.getInstance(context).noteDao()
    override suspend fun add(note: Note) = noteDao.add(NoteEntity.fromNote(note))
    override suspend fun get(id: Long): Note? = noteDao.get(id)?.toNote()
    override suspend fun getAll(): List<Note> = noteDao.getAll().map { it.toNote() }
    override suspend fun delete(note: Note) = noteDao.delete(NoteEntity.fromNote(note))
}