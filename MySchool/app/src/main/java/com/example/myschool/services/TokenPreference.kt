package com.example.myschool.services

import android.content.Context
import com.example.myschool.R

class TokenPreference(val context: Context) {
    val pref = context.getSharedPreferences(R.string.app_name.toString(), Context.MODE_PRIVATE)

    fun setToken(token: String) {
        pref.edit().putString("token", token).apply()
    }
}