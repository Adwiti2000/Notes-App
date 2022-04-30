package com.example.notes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
class Notes(
    @PrimaryKey(autoGenerate= true)val id: Int,
    @ColumnInfo(name = "text") val text:String) {
}
