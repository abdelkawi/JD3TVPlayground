package com.example.jd3tvplayground.presentation.splash

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.jd3tvplayground.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

//
// Created by Dina Mounib on 6/28/22.
//
@AndroidEntryPoint
class SplashActivity : ComponentActivity() {
    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycleScope.launch {
            viewModel.getDomainApi()
        }

    }
}