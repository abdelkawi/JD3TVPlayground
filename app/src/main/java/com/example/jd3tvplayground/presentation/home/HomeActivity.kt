package com.example.jd3tvplayground.presentation.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Text
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

//
// Created by Dina Mounib on 6/28/22.
//
@AndroidEntryPoint
class HomeActivity:ComponentActivity() {

    private val viewModel:HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Text(
                text = "testing"
            )
        }
        lifecycleScope.launch {
//            viewModel.getMainSection("home","623acdd8ddab51a90f79f160","61ddcff8097ac672b880c35e")
        }
    }
}