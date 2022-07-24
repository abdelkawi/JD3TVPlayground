package com.genwin.jd3tv.screens.splash.data

import com.genwin.jd3tv.common.Result
import com.genwin.jd3tv.common.safeApiCall
import com.genwin.jd3tv.screens.splash.domain.interfaces.RemoteDataSource
import com.genwin.jd3tv.screens.splash.domain.interfaces.api.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val splashApiService: ApiService) :
    RemoteDataSource {

    override suspend fun getDomainOfWebClient(): Result<SplashResponse> =
        withContext(Dispatchers.IO) {
            safeApiCall {
                splashApiService.getDomainOfWebClient()
            }
        }

}