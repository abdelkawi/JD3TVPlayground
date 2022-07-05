package com.genwin.jd3tv.screens.login.presentation

//
// Created by Dina Mounib on 7/4/22.
//
sealed class LoginFormEvent {
    data class EmailChanged(val email: String) : LoginFormEvent()
    data class PasswordChanged(val password: String) : LoginFormEvent()
    data class AcceptTerms(val isAccepted: Boolean) : LoginFormEvent()
    object Submit: LoginFormEvent()
}