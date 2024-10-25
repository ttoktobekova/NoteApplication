package com.example.noteapp.ui.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.noteapp.R

class PreferenceHelper {

    private lateinit var sharedPreferences: SharedPreferences

    fun unit(context: Context) {
        sharedPreferences = context.getSharedPreferences(SHARED_KEY, Context.MODE_PRIVATE)
    }

    var text: String?
        get() = sharedPreferences.getString(TEXT_KEY, "")
        set(value) = sharedPreferences.edit().putString(TEXT_KEY, value)!!.apply()

    fun isShow(): Boolean {
        return sharedPreferences.getBoolean(TEXT_BOOLEAN2, false)
    }

    fun onShowed() {
        sharedPreferences.edit().putBoolean(TEXT_BOOLEAN2, true).apply()
    }
    fun saveSelectedColor(color: Int) {
        sharedPreferences.edit().putInt(SELECTED_COLOR_KEY, color).apply()
    }

    fun getSelectedColor(): Int {
        return sharedPreferences.getInt(SELECTED_COLOR_KEY, R.color.btn_gray)
    }
    companion object {
        const val TEXT_BOOLEAN2 = "booleanApplication22"
        const val TEXT_KEY = "text"
        const val SHARED_KEY = "shared_key"
        const val SELECTED_COLOR_KEY = "selected_color"
    }
}
