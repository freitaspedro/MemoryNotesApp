package com.example.memorynotesapp.framework.di

import com.example.memorynotesapp.framework.NoteViewModel
import com.example.memorynotesapp.framework.NotesViewModel
import dagger.Component

@Component(modules = [AppModule::class, RepoModule::class, UseCasesModule::class])
interface ViewModelComponent {

    fun inject(noteViewModel: NoteViewModel)

    fun inject(notesViewModel: NotesViewModel)
}