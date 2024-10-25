package com.example.noteapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "noteModel")
data class NoteModel(
    val title: String,
    val description: String,
    val backgroundColor: Int,
    val creationTime: String,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
