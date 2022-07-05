package com.genwin.jd3tv.screens.splash

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.genwin.jd3tv.R
import com.genwin.jd3tv.common.Result

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

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
            val res = viewModel.getDomainApi()
            when (res) {
                is Result.Error -> {
                    Log.d("Error", res.error ?: "wtf")
                }
                is Result.Success -> {
                       Log.i("splash==>",res.data.id?:"")

                        Log.i("splash==>",res.data.themeData?.id?:"")

                }
            }
        }
    }

}