package com.dicoding.mynotesapp_room.ui.insert

import android.app.Application
import androidx.lifecycle.ViewModel
import com.dicoding.mynotesapp_room.database.Note
import com.dicoding.mynotesapp_room.respository.NoteRespository

class NoteViewModel(application: Application) : ViewModel() {
    private val NoteRepo: NoteRespository = NoteRespository(application)

    fun insert(note: Note) {
        NoteRepo.insert(note)
    }
    fun update(note: Note) {
        NoteRepo.update(note)
    }
    fun delete(note: Note) {
        NoteRepo.delete(note)
    }
}