package com.example.roomsharedpreferences

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : ViewModel() {
    private val allNotes = MutableStateFlow<List<Note>>(emptyList())
    val notes: StateFlow<List<Note>> = allNotes

    private val noteDao = NoteDatabase.getDatabase(application).noteDao()

    init {
        viewModelScope.launch {
            allNotes.value = noteDao.getAllNotes()
        }
    }

    fun insert(note: Note) {
        viewModelScope.launch {
            noteDao.insert(note)
            allNotes.value = noteDao.getAllNotes()
        }
    }

    fun delete(note: Note) {
        viewModelScope.launch {
            noteDao.delete(note)
            allNotes.value = noteDao.getAllNotes()
        }
    }

}