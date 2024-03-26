package com.dicoding.mynotesapp_room.ui.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.mynotesapp_room.database.Note
import com.dicoding.mynotesapp_room.respository.NoteRespository

class MainViewModel(application: Application) : ViewModel() {
    private val NoteRepo: NoteRespository = NoteRespository(application)

    fun getAllNotes(): LiveData<List<Note>> = NoteRepo.getAllNotes()
}