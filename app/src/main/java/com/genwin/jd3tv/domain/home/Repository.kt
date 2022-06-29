package com.genwin.jd3tv.domain.home

import com.genwin.jd3tv.domain.Result


//
// Created by Dina Mounib on 3/3/22.
//
interface Repository {
    suspend fun getDomainOfWebClient(): Result<DomainOfWebClientResponse>
}