package com.example.dreamlog

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Dream::class), version = 1, exportSchema = false)
public abstract class DreamRoomDatabase : RoomDatabase(){
    // connected with DAO
    abstract fun dreamDAO() : DreamDAO // getter method

    companion object {
        @Volatile
        private var INSTANCE: DreamRoomDatabase? = null

        fun getDatabase(context: Context) : DreamRoomDatabase {
            // if our instance is not null
            // we can return it

            // if it is null, we want to create the database
            return INSTANCE ?: synchronized(this) {
                // creating the database
                // return the database
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DreamRoomDatabase::class.java,
                    "dream_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}