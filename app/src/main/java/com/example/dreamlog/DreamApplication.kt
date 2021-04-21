package com.example.dreamlog

import android.app.Application

class DreamApplication : Application() {
    // creating 1 instance of database
    // 1 instance of repository

    // lazy (keyword)
    // the value gets computed or executed only upon first access
    val database by lazy{
        DreamRoomDatabase.getDatabase(this)
    }
    val repository by lazy {
        DreamRepository(database.dreamDAO())
    }
}