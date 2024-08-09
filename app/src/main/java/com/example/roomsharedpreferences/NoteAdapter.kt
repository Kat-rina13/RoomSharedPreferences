package com.example.roomsharedpreferences

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(private var notes: List<Note>, private val onDelete: (Note) -> Unit)  : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNoteContent: TextView = itemView.findViewById(R.id.tvNoteContent)
        val deleteImageView: ImageView = itemView.findViewById(R.id.imageViewDelete)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.tvNoteContent.text = note.content
        holder.deleteImageView.setOnClickListener {
            onDelete(note)
        }
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    fun updateNote(newNotes: List<Note>) {
        notes = newNotes
        notifyDataSetChanged()
    }


}
