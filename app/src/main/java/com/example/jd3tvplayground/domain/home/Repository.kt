package com.example.jd3tvplayground.domain.home

import com.example.jd3tvplayground.domain.Result


//
// Created by Dina Mounib on 3/3/22.
//
interface Repository {
    suspend fun getDomainOfWebClient(): Result<DomainOfWebClientResponse>
}