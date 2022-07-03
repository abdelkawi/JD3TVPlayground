package com.example.jd3tvplayground.presentation.splash

import androidx.lifecycle.ViewModel
import com.example.jd3tvplayground.domain.splash.RemoteDataSource
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

    suspend fun getMainSection(refName: String, themeID: String, clientID: String) =
        remoteDataSource.getSectionDetails(refName, themeID, clientID)

}