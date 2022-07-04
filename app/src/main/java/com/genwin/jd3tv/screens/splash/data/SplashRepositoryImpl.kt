package com.genwin.jd3tv.screens.splash.data

import com.genwin.jd3tv.common.Result
import com.genwin.jd3tv.screens.splash.domain.home.DomainOfWebClientResponse
import com.genwin.jd3tv.screens.splash.domain.home.RemoteDataSource
import com.genwin.jd3tv.screens.splash.domain.home.Repository
import javax.inject.Inject

//
// Created by Dina Mounib on 3/3/22.
//
class SplashRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource):
    Repository {
    override suspend fun getDomainOfWebClient(): Result<DomainOfWebClientResponse> {
        return remoteDataSource.getDomainOfWebClient()
    }
}