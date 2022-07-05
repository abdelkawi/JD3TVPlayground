package com.genwin.jd3tv.screens.splash.domain

import com.genwin.jd3tv.common.Result
import com.genwin.jd3tv.screens.splash.data.SplashResponse

//
// Created by Dina Mounib on 3/3/22.
//
interface RemoteDataSource {

    suspend fun getDomainOfWebClient(): Result<SplashResponse>

}