package com.example.dreamlog

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
//import java.sql.Date
import java.util.*

// data class Dream(val dream: String)

// @Entity -> class represents a SQLite table
@Entity(tableName = "dream_table")
class Dream(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "content") val content: String,
    @ColumnInfo(name = "reflection") val reflection: String,
    @ColumnInfo(name = "emotion") val emotion: String
) { // define column information
    //
}