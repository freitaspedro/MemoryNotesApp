package com.example.memorynotesapp.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.core.data.Note
import com.example.memorynotesapp.R
import kotlinx.android.synthetic.main.item_note.view.*
import java.text.SimpleDateFormat
import java.util.*

class NotesAdapter(var notes: ArrayList<Note>, val actions: ListAction): RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    inner class NoteViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val layout = view.cardViewNote
        private val title = view.textViewTitle
        private val content = view.textViewContent
        private val date = view.textViewDate
        private val wordCount = view.textViewWordCount

        @SuppressLint("SimpleDateFormat", "SetTextI18n")
        fun bind(note: Note) {
            title.text = note.title
            content.text = note.content
            val sdf = SimpleDateFormat("MM dd, HH:mm:ss")
            val updateAt = Date(note.updateAt)
            date.text = "Last updated: ${sdf.format(updateAt)}"
            layout.setOnClickListener { actions.onClick(note.id) }
            wordCount.text = "Words: ${note.wordCount}"
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = NoteViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
    )

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(notes[position])
    }

    override fun getItemCount() = notes.size

    @SuppressLint("NotifyDataSetChanged")
    fun refresh(notes: List<Note>) {
        this.notes.clear()
        this.notes.addAll(notes)
        notifyDataSetChanged()
    }

}