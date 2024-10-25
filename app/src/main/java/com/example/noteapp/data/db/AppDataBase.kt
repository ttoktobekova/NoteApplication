package com.example.noteapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.noteapp.data.db.daos.NoteDAO
import com.example.noteapp.data.models.NoteModel

@Database(entities = [NoteModel::class], version = 4)

abstract class AppDataBase : RoomDatabase() {
    abstract fun noteDao(): NoteDAO
}