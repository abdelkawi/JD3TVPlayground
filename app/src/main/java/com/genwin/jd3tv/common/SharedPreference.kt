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

    fun getNickName(): String {
        return prefs.getString("nickname", "")!!
    }

    fun setNickName(nickName: String) {
        prefs.edit().putString("nickname", nickName).apply()
    }

    fun getFullName(): String {
        return prefs.getString("fullname", "")!!
    }

    fun setFullName(fullName: String) {
        prefs.edit().putString("fullname", fullName).apply()
    }
    fun getPhoto(): String {
        return prefs.getString("photo", "")!!
    }

    fun setPhoto(photo: String) {
        prefs.edit().putString("photo", photo).apply()
    }

    fun getEmail(): String {
        return prefs.getString("email", "")!!
    }

    fun setEmail(email: String) {
        prefs.edit().putString("email", email).apply()
    }

    fun signOut(){
        prefs.edit().clear().apply()
    }
}