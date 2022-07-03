package com.example.jd3tvplayground.presentation.splash

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.lifecycle.lifecycleScope
import com.example.jd3tvplayground.R
import com.example.jd3tvplayground.theme.AppTheme
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
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            viewModel.getMainSection("home", "623acdd8ddab51a90f79f160", "61ddcff8097ac672b880c35e")
            viewModel.getDomainApi()

        }

    }

}