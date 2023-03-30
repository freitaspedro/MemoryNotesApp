package com.example.memorynotesapp.framework.di

import com.example.core.repository.NoteRepository
import com.example.core.usecase.AddNote
import com.example.core.usecase.DeleteNote
import com.example.core.usecase.GetAllNotes
import com.example.core.usecase.GetNote
import com.example.memorynotesapp.framework.UseCases
import dagger.Module
import dagger.Provides


@Module
class UseCasesModule {

    @Provides
    fun providesUseCases(repo: NoteRepository) =
        UseCases(AddNote(repo), GetNote(repo), GetAllNotes(repo), DeleteNote(repo))

}