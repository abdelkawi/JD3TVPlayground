package com.example.jd3tvplayground.domain.splash

import com.example.jd3tvplayground.domain.common.Result
import com.example.jd3tvplayground.domain.home.MainSectionResponse


//
// Created by Dina Mounib on 3/3/22.
//
interface Repository {
    suspend fun getDomainOfWebClient(): Result<DomainOfWebClientResponse>
    suspend fun getSectionDetails(ref: String,
                                  themeID: String,
                                  clientID: String): Result<MainSectionResponse>

}