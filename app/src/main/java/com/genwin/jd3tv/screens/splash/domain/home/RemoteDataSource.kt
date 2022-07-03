package com.genwin.jd3tv.screens.splash.domain.home

import com.genwin.jd3tv.common.Result
import com.genwin.jd3tv.common.safeApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

//
// Created by Dina Mounib on 3/3/22.
//
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getDomainOfWebClient(): Result<DomainOfWebClientResponse> = withContext(Dispatchers.IO) {
        safeApiCall {
            apiService.getDomainOfWebClient()
        }
    }
}