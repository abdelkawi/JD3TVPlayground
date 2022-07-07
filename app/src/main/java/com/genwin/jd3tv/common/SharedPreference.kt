package com.genwin.jd3tv.common

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

//
// Created by Dina Mounib on 7/7/22.
//
class SharedPreference @Inject constructor(@ApplicationContext context: Context) {
    private val prefs = context.getSharedPreferences("JDApp", Context.MODE_PRIVATE)
    fun getUserName(): String {
        return prefs.getString("username", "")!!
    }

    fun setUserName(userName: String) {
        prefs.edit().putString("username", userName).apply()
    }

    fun getPassword(): String {
        return prefs.getString("password", "")!!
    }

    fun setPassword(password: String) {
        prefs.edit().putString("password", password).apply()
    }
}