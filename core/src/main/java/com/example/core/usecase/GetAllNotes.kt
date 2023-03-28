package com.example.core.usecase

import com.example.core.repository.NoteRepository

class GetAllNotes(private val repository: NoteRepository) {
    suspend operator fun invoke() = repository.getAll()
}