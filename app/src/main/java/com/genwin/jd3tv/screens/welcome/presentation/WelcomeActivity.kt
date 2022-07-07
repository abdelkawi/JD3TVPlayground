package com.genwin.jd3tv.screens.welcome.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.genwin.jd3tv.R
import com.genwin.jd3tv.screens.login.presentation.LoginActivity
import kotlinx.android.synthetic.main.activity_welcome.*

//
// Created by Dina Mounib on 7/3/22.
//
class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        startBtn.setOnClickListener {
            startActivity(Intent(this@WelcomeActivity, LoginActivity::class.java))
            this@WelcomeActivity.finish()
        }
    }
}