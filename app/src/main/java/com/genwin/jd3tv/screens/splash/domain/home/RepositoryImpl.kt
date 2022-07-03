package com.genwin.jd3tv.screens.splash.domain.home

import com.genwin.jd3tv.common.Result
import javax.inject.Inject

//
// Created by Dina Mounib on 3/3/22.
//
class RepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource): Repository {
    override suspend fun getDomainOfWebClient(): Result<DomainOfWebClientResponse> {
        return remoteDataSource.getDomainOfWebClient()
    }
}