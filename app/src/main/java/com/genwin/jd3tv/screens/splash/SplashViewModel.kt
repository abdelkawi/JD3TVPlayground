package com.genwin.jd3tv.screens.splash

import androidx.lifecycle.ViewModel
import com.genwin.jd3tv.screens.splash.domain.home.RemoteDataSource
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