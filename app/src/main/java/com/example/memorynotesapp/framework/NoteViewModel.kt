package com.example.memorynotesapp.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.core.data.Note
import com.example.core.repository.NoteRepository
import com.example.core.usecase.AddNote
import com.example.core.usecase.DeleteNote
import com.example.core.usecase.GetAllNotes
import com.example.core.usecase.GetNote
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application): AndroidViewModel(application) {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private val repository = NoteRepository(RoomNoteDataSource(application))

    private val useCases = UseCases(AddNote(repository), GetNote(repository), GetAllNotes(repository), DeleteNote(repository))

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