package com.example.memorynotesapp.framework

import com.example.core.usecase.*

data class UseCases (
    val addNote: AddNote,
    val getNote: GetNote,
    val getAllNotes: GetAllNotes,
    val deleteNote: DeleteNote,
    val getWordCount: GetWordCount
)