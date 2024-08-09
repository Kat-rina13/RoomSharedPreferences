package com.example.roomsharedpreferences

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesHelper(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("note_prefs", Context.MODE_PRIVATE)

    fun saveColor(color: String) {
        sharedPreferences.edit().putString("note_color", color).apply()
    }

    fun getColor(): String? {
        return sharedPreferences.getString("note_color", "#FFFFFF")
    }
}