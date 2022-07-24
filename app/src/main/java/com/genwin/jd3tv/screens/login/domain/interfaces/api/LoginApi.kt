package com.genwin.jd3tv.screens.login.domain.interfaces.api

import com.genwin.jd3tv.common.ApiResponse
import com.genwin.jd3tv.screens.login.data.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

//
// Created by Dina Mounib on 7/5/22.
//
interface LoginApi {
    @Headers("Content-Type:application/json")
    @POST("app-api/users/login")
    suspend fun login(
        @Body params: Map<String, String>
    ): Response<ApiResponse<LoginResponse>>
}