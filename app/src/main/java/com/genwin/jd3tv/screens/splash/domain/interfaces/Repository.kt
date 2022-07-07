package com.genwin.jd3tv.screens.splash.domain.interfaces

import com.genwin.jd3tv.common.Result
import com.genwin.jd3tv.screens.splash.data.SplashResponse


//
// Created by Dina Mounib on 3/3/22.
//
interface Repository {
    suspend fun getClientData(): Result<SplashResponse>
}