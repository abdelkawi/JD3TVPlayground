package com.example.jd3tvplayground.presentation.home

import androidx.lifecycle.ViewModel
import com.example.jd3tvplayground.domain.home.HomeRemoteDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Named

//
// Created by Dina Mounib on 6/28/22.
//
@HiltViewModel
class HomeViewModel @Inject constructor( val remoteDataSource: HomeRemoteDataSource) : ViewModel() {

//    suspend fun getMainSection(refName: String, themeID: String, clientID: String) =
//        remoteDataSource.getSectionDetails(refName, themeID, clientID)
}