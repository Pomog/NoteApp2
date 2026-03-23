package com.example.noteapp2.screen

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp2.model.Note
import com.example.noteapp2.repo.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class NoteViewModel @Inject constructor(private val repo: NoteRepository) : ViewModel(){
    //var noteList = mutableStateListOf<Note>()
    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()

    init {
        //noteList.addAll(NoteDataSource().loadNotes())
        viewModelScope.launch(Dispatchers.IO){
            repo.getAllNotes().distinctUntilChanged()
                .collect {
                    listOfNotes ->
                    if (listOfNotes.isEmpty()){
                       Log.d("EMPTY", "EMPTY LIST")
                    } else {
                        _noteList.value = listOfNotes
                    }
                }
        }
    }

    suspend fun addNote(note: Note) = viewModelScope.launch {
        repo.addNote(note)
    }

    suspend fun updateNote(note: Note) = viewModelScope.launch {
        repo.updateNote(note)
    }

    suspend fun removeNote(note: Note) = viewModelScope.launch {
        repo.deleteNote(note)
    }

}