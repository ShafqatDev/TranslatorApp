package com.example.translatorapp.utils

import android.content.Context


class SharedPreferences constructor(private val context: Context) {
    private val sharedPref = context.getSharedPreferences("language_prefs", Context.MODE_PRIVATE)
    private val editor = sharedPref.edit()

    fun saveFromLanguage(fromLanguage: String) {
        editor.putString("from_language", fromLanguage).apply()
    }

    fun saveToLanguage(toLanguage: String) {
        editor.putString("to_language", toLanguage).apply()
    }


    fun getFromLanguage(): String {
        return sharedPref.getString("from_language", "en") ?: "en"
    }

    fun getToLanguage(): String {
        return sharedPref.getString("to_language", "ur") ?: "ur"
    }
}