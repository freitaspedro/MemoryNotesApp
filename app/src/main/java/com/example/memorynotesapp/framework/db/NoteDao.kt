package com.example.memorynotesapp.framework.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface NoteDao {

    @Insert(onConflict = REPLACE)
    fun add(noteEntity: NoteEntity)

    @Query("SELECT * FROM note WHERE id = :id")
    fun get(id: Long): NoteEntity?

    @Query("SELECT * FROM note")
    fun getAll(): List<NoteEntity>

    @Delete
    fun delete(noteEntity: NoteEntity)

}