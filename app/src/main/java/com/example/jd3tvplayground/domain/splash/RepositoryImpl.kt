package com.example.jd3tvplayground.domain.splash

import com.example.jd3tvplayground.domain.common.Result
import com.example.jd3tvplayground.domain.home.MainSectionResponse
import javax.inject.Inject
import javax.inject.Named

//
// Created by Dina Mounib on 3/3/22.
//
class RepositoryImpl @Inject constructor(
    @Named("splash") private val remoteDataSource: RemoteDataSource):Repository {
    override suspend fun getDomainOfWebClient(): Result<DomainOfWebClientResponse> {
        return remoteDataSource.getDomainOfWebClient()
    }

    override suspend fun getSectionDetails(
        ref: String,
        themeID: String,
        clientID: String
    ): Result<MainSectionResponse> {
        return remoteDataSource.getSectionDetails(ref, themeID, clientID)
    }
}