package com.genwin.jd3tv.screens.splash.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.genwin.jd3tv.R
import com.genwin.jd3tv.common.Result
import com.genwin.jd3tv.screens.home.presentation.MainActivity
import com.genwin.jd3tv.screens.welcome.presentation.WelcomeActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.*


//
// Created by Dina Mounib on 6/28/22.
//
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        lifecycleScope.launch {
            val res = viewModel.getClientData()
            when (res) {
                is Result.Error -> {
                    Log.d("Error", res.error ?: "wtf")
                }
                is Result.Success -> {
                    startActivity(Intent(this@SplashActivity, WelcomeActivity::class.java))
                    Log.i("splash==>", res.data.id ?: "")

                    Log.i("splash==>", res.data.themeData?.id ?: "")

                }
            }
        }
    }

}