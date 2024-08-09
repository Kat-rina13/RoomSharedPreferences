package com.example.roomsharedpreferences

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {


    private val noteViewModel: NoteViewModel by viewModels()
    private lateinit var noteAdapter: NoteAdapter
    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val etTextNote: EditText = findViewById(R.id.etTextNote)
        val addButton: Button = findViewById(R.id.addButton)
        val recyclerViewNotes: RecyclerView = findViewById(R.id.recyclerViewNotes)
        val closeButton: Button = findViewById(R.id.closeButton)

        sharedPreferencesHelper = SharedPreferencesHelper(this)

        noteAdapter = NoteAdapter(emptyList()) { note ->
            noteViewModel.delete(note)
        }

        recyclerViewNotes.layoutManager = LinearLayoutManager(this)
        recyclerViewNotes.adapter = noteAdapter

        lifecycleScope.launch {
            noteViewModel.notes.collect { notes ->
                noteAdapter.updateNote(notes)
            }
        }


        addButton.setOnClickListener {
            val content = etTextNote.text.toString()
            val color = sharedPreferencesHelper.getColor() ?: "#FFFFFF"
            if (content.isNotEmpty()) {
                noteViewModel.insert(Note(content = content, color = color))
                etTextNote.text.clear()
            }

        }

        closeButton.setOnClickListener {
            finish()
        }
    }



}