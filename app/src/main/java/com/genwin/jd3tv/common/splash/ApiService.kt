package com.example.jd3tvplayground.domain.splash

import com.example.jd3tvplayground.domain.common.ApiResponse
import com.example.jd3tvplayground.domain.home.MainSectionResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//
// Created by Dina Mounib on 3/3/22.
//
interface ApiService {
    @GET("web-clients/chefjojo-staging.genwin.net")
    suspend fun getDomainOfWebClient(): Response<ApiResponse<DomainOfWebClientResponse>>

    @GET("pages")
    suspend fun getSectionDetails(
        @Query("page_ref") ref: String,
        @Query("theme_id") themeID: String,
        @Query("webclient_id") clientID: String
    ): Response<ApiResponse<MainSectionResponse>>
}