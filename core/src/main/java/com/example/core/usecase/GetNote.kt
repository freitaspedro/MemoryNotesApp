package com.example.core.usecase

import com.example.core.repository.NoteRepository

class GetNote(private val repository: NoteRepository) {
    suspend operator fun invoke(id: Long) = repository.get(id)
}