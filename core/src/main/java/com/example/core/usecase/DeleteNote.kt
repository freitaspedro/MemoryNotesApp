package com.example.core.usecase

import com.example.core.data.Note
import com.example.core.repository.NoteRepository

class DeleteNote(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(note: Note) = noteRepository.delete(note)
}