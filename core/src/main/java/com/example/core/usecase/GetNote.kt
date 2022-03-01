package com.example.core.usecase

import com.example.core.repository.NoteRepository

class GetNote(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(id: Long) = noteRepository.get(id)
}