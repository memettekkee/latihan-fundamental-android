package com.dicoding.mynotesapp_room.respository

import android.app.Application
import androidx.lifecycle.LiveData
import com.dicoding.mynotesapp_room.database.Note
import com.dicoding.mynotesapp_room.database.NoteDao
import com.dicoding.mynotesapp_room.database.NoteRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class NoteRespository(application: Application) {
    private val mNotes: NoteDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = NoteRoomDatabase.getDatabase(application)
        mNotes = db.noteDao()
    }

    fun getAllNotes(): LiveData<List<Note>> = mNotes.getAllNotes()

    fun insert(note: Note) {
        executorService.execute { mNotes.insert(note) }
    }

    fun delete(note: Note) {
        executorService.execute { mNotes.delete(note) }
    }

    fun update(note: Note) {
        executorService.execute { mNotes.update(note) }
    }
}