package com.example.jd3tvplayground.presentation.splash

import androidx.lifecycle.ViewModel
import com.example.jd3tvplayground.domain.home.RemoteDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

//
// Created by Dina Mounib on 6/28/22.
//
@HiltViewModel
class SplashViewModel @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : ViewModel() {

    suspend fun getDomainApi() = remoteDataSource.getDomainOfWebClient()
}