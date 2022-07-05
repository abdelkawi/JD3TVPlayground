package com.genwin.jd3tv.screens.login.presentation

//
// Created by Dina Mounib on 7/4/22.
//
data class LoginFormState (
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val acceptedTerms: Boolean = false,
    val termsError: String? = null
)