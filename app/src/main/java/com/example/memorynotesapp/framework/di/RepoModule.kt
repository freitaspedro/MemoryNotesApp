package com.example.memorynotesapp.framework.di

import android.app.Application
import com.example.core.repository.NoteRepository
import com.example.memorynotesapp.framework.RoomNoteDataSource
import dagger.Module
import dagger.Provides

@Module
class RepoModule {

    @Provides
    fun providesRepo(app: Application) = NoteRepository(RoomNoteDataSource(app))
}