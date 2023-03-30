package com.example.memorynotesapp.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.core.data.Note
import com.example.memorynotesapp.framework.di.AppModule
import com.example.memorynotesapp.framework.di.DaggerViewModelComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class NoteViewModel(application: Application): AndroidViewModel(application) {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    @Inject
    lateinit var useCases: UseCases

    init {
        DaggerViewModelComponent.builder()
            .appModule(AppModule(getApplication()))
            .build()
            .inject(this)
    }

    val isSaved = MutableLiveData<Boolean>()
    val currentNote = MutableLiveData<Note?>()

    fun save(note: Note) {
        coroutineScope.launch {
            useCases.addNote(note)
            isSaved.postValue(true)
        }
    }

    fun get(id: Long) {
        coroutineScope.launch {
            val note = useCases.getNote(id)
            currentNote.postValue(note)
        }
    }

    fun delete(note: Note) {
        coroutineScope.launch {
            useCases.deleteNote(note)
            isSaved.postValue(true)
        }
    }

}