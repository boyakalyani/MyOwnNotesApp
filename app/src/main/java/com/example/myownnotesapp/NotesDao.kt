package com.example.myownnotesapp



import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDao{


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)


    @Delete
     fun deleteUser(note: Note)

    @Query("Select * from notesTable order by id ASC")
    fun getAllNotes(): List<Note>

    @Update
     fun update(noteEntity: Note)

}

