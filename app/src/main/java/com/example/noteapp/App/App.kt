package com.example.noteapp.App

import android.app.Application
import androidx.room.Room
import com.example.noteapp.data.db.AppDataBase
import com.example.noteapp.ui.utils.PreferenceHelper

class App:Application() {
    override fun onCreate() {
        super.onCreate()
        getInstance()
        sharedPreference()
    }

    private fun getInstance():AppDataBase? {
        if (appDataBase == null){
            appDataBase = applicationContext?.let {context->
                Room.databaseBuilder(
                    context,
                    AppDataBase::class.java,
                    "note.database"
                ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
            }
        }
        return appDataBase
    }

    private fun sharedPreference() {
        val sharedPreferences = PreferenceHelper()
        sharedPreferences.unit(this)
    }

    companion object{
        var appDataBase :AppDataBase? = null
    }
}