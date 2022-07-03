package com.example.jd3tvplayground.domain.splash

import com.example.jd3tvplayground.domain.common.Result
import com.example.jd3tvplayground.domain.common.safeApiCall
import com.example.jd3tvplayground.domain.home.MainSectionResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

//
// Created by Dina Mounib on 3/3/22.
//
@Named("splash")
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getDomainOfWebClient(): Result<DomainOfWebClientResponse> = withContext(Dispatchers.IO) {
        safeApiCall {
            apiService.getDomainOfWebClient()
        }
    }

    suspend fun getSectionDetails(
        ref: String,
        themeID: String,
        clientID: String
    ): Result<MainSectionResponse> = withContext(Dispatchers.IO) {

        safeApiCall {
            apiService.getSectionDetails(ref, themeID, clientID)
        }
    }
}