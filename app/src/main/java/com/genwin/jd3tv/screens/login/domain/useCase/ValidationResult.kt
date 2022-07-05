package com.genwin.jd3tv.screens.login.domain.useCase

//
// Created by Dina Mounib on 7/4/22.
//
data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)