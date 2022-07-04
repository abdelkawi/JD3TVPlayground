package com.genwin.jd3tv.screens.splash.data

import com.genwin.jd3tv.common.Result
import com.genwin.jd3tv.common.safeApiCall
import com.genwin.jd3tv.screens.splash.domain.home.ApiService
import com.genwin.jd3tv.screens.splash.domain.home.DomainOfWebClientResponse
import com.genwin.jd3tv.screens.splash.domain.home.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val splashApiService: ApiService) :
    RemoteDataSource {

    override suspend fun getDomainOfWebClient(): Result<DomainOfWebClientResponse> =
        withContext(Dispatchers.IO) {
            safeApiCall {
                splashApiService.getDomainOfWebClient()
            }
        }

}