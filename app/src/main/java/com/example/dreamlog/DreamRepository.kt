package com.example.dreamlog

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
//import java.sql.Date
import java.util.*

class DreamRepository (private val dreamDAO: DreamDAO){

    // ROOM does not run queries on main thread
    // it requires you to run queries on a separate thread

    // store all the results into a list and make it a public property that

    // for each of the method in the DAO, you need to write something to execute them in separate threads

    // getAllDreams
    val allDreams: Flow<List<Dream>> = dreamDAO.getAllDreams()

    // suspend -> room runs all suspend functions/queries off the main thread
    // so we are just calling it and embedding in a method that we can use later
    suspend fun insert(dream: Dream) {
       dreamDAO.insert(dream)
    }

    // update dreams
    suspend fun updateDream(title: String, content: String, reflection: String, emotion: String, id: Int) {
        dreamDAO.updateDream(title, content, reflection, emotion, id)
    }

    // delete dreams
    suspend fun deleteByID(id: Int) {
        dreamDAO.deleteDreamById(id)
    }

    // get dream by dream id
    fun getDreamByID(id: Int) : Flow<Dream> {
        return dreamDAO.getDreamByID(id)
    }
}