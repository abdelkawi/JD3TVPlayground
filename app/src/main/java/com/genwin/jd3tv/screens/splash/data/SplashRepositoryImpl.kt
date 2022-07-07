package com.genwin.jd3tv.screens.splash.data

import com.genwin.jd3tv.common.Result
import com.genwin.jd3tv.screens.splash.domain.interfaces.RemoteDataSource
import com.genwin.jd3tv.screens.splash.domain.interfaces.Repository
import javax.inject.Inject

//
// Created by Dina Mounib on 3/3/22.
//
class SplashRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    Repository {
    override suspend fun getClientData(): Result<SplashResponse> {
        val res = remoteDataSource.getDomainOfWebClient()
        return when (res) {
            is Result.Error -> Result.Error(res.error)
            is Result.Success -> Result.Success(
                data = res.data
            )
        }
    }
}