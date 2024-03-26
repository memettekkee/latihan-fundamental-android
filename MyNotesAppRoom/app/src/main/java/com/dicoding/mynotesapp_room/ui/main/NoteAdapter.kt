package com.dicoding.mynotesapp_room.ui.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.dicoding.mynotesapp_room.database.Note
import com.dicoding.mynotesapp_room.databinding.ItemNoteBinding
import com.dicoding.mynotesapp_room.helper.NoteDiffCallback
import com.dicoding.mynotesapp_room.ui.insert.NoteActivity

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NViewHolder>() {
    private val listNotes = ArrayList<Note>()
    fun setListNotes(listNotes: List<Note>) {
        val diffCallback = NoteDiffCallback(this.listNotes, listNotes)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listNotes.clear()
        this.listNotes.addAll(listNotes)
        diffResult.dispatchUpdatesTo(this)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NViewHolder, position: Int) {
       holder.bind(listNotes[position])
    }


    inner class NViewHolder(private val binding: ItemNoteBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note) {
            with(binding) {
                tvItemTitle.text = note.title
                tvItemDate.text = note.date
                tvItemDescription.text = note.description
                cvItemNote.setOnClickListener {
                    val intent = Intent(it.context, NoteActivity::class.java)
                    intent.putExtra(NoteActivity.EXTRA_NOTE, note)
                    it.context.startActivity(intent)
                }
            }
        }
    }

    override fun getItemCount():Int {
        return listNotes.size
    }

}