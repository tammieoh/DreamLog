package com.example.dreamlog

import androidx.room.*
import kotlinx.coroutines.flow.Flow
//import java.sql.Date
import java.util.*

@Dao
interface DreamDAO {
    // what sql queries do we need?
    // 1. get all the tasks and sort them by alphabetical order
    // 2. insert a task into the database

    // get all dreams and order by ID
    @Query("SELECT * FROM dream_table ORDER BY id ASC")
    fun getAllDreams() : Flow<List<Dream>>

    // insert dreams
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(dream: Dream)

    // update dreams
    @Query("UPDATE dream_table SET title=:title, content=:content, reflection=:reflection, emotion=:emotion WHERE id=:id")
    suspend fun updateDream(title: String, content: String, reflection: String, emotion: String, id: Int)

    // delete dream by dream id
    @Query("DELETE FROM dream_table WHERE id=:id")
    suspend fun deleteDreamById(id: Int)

    // get dream by dream id
    @Query ("SELECT * FROM dream_table WHERE id=:id")
    fun getDreamByID(id: Int) : Flow<Dream>
}