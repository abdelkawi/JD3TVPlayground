package com.genwin.jd3tv.screens.splash.domain.home

import com.genwin.jd3tv.common.ApiResponse
import retrofit2.Response
import retrofit2.http.GET

//
// Created by Dina Mounib on 3/3/22.
//
interface ApiService {
    @GET("chefjojo-staging.genwin.net")
    suspend fun getDomainOfWebClient(): Response<ApiResponse<DomainOfWebClientResponse>>
}