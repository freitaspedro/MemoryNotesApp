package com.example.memorynotesapp.framework.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface NoteDao {

    @Insert(onConflict = REPLACE)
    suspend fun add(noteEntity: NoteEntity)

    @Query("SELECT * FROM note WHERE id = :id")
    suspend fun get(id: Long): NoteEntity?

    @Query("SELECT * FROM note")
    suspend fun getAll(): List<NoteEntity>

    @Delete
    suspend fun delete(noteEntity: NoteEntity)

}