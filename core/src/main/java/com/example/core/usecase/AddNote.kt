package com.example.core.usecase

import com.example.core.repository.NoteRepository

class AddNote(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(note: Note) = noteRepository.add(note)
}