package com.example.memorynotesapp.framework

import com.example.core.usecase.AddNote
import com.example.core.usecase.DeleteNote
import com.example.core.usecase.GetAllNotes
import com.example.core.usecase.GetNote

data class UseCases (
    val addNote: AddNote,
    val getNote: GetNote,
    val getAllNotes: GetAllNotes,
    val deleteNote: DeleteNote
)