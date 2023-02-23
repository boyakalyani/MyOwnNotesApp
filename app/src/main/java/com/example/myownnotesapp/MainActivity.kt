package com.example.myownnotesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var fltBtn: FloatingActionButton
    private lateinit var database: AppDataBase
    private lateinit var notesDao: NotesDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = AppDataBase.getDatabase(this)
        notesDao = database.getNotesDao()

        getData();

        fltBtn = findViewById(R.id.idFAB)
        fltBtn.setOnClickListener() {
            startActivity(Intent(this, EditNotes::class.java))
            getData()
            this.finish()
        }
    }
    private fun getData() {
        val reclerView = findViewById<RecyclerView>(R.id.notesRV)

        val userListNote = notesDao.getAllNotes()
        val adapter = AdapterNotes<Note>(userListNote)
        reclerView.adapter = adapter
        reclerView.layoutManager = LinearLayoutManager(this)
        adapter.notifyDataSetChanged()
    }
}



