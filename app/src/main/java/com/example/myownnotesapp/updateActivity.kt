package com.example.myownnotesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class updateActivity : AppCompatActivity() {
    lateinit var noteTitleEdt: EditText
    lateinit var noteEdt: EditText
    lateinit var saveBtn: Button
    private lateinit var database: AppDataBase
    private lateinit var notesDao: NotesDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        noteTitleEdt = findViewById(R.id.idEdtNoteName)
        noteEdt = findViewById(R.id.idEdtNoteDesc)
        saveBtn = findViewById(R.id.updateIdBtn)


        val title=intent.getStringExtra("title")
        val notes=intent.getStringExtra("notes")

        database= AppDataBase.getDatabase(this)
        notesDao=database.getNotesDao()


        noteTitleEdt.setText(title)
        noteEdt.setText(notes)

        saveBtn.setOnClickListener(){
            notesDao.update(Note(noteTitleEdt.text.toString(),noteEdt.text.toString(),"" ))
            finish()
        }
    }
}