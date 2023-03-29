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

class NotesViewModel(application: Application): AndroidViewModel(application) {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private val repository = NoteRepository(RoomNoteDataSource(application))

    private val useCases = UseCases(AddNote(repository), GetNote(repository), GetAllNotes(repository), DeleteNote(repository))

    val notes = MutableLiveData<List<Note>>()

    fun getAll() {
        coroutineScope.launch {
            val allNotes = useCases.getAllNotes()
            notes.postValue(allNotes)
        }
    }

}