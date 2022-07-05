package com.genwin.jd3tv.screens.login.domain.useCase

import android.util.Patterns

//
// Created by Dina Mounib on 7/4/22.
//
class ValidateEmail {

    fun execute(email: String): ValidationResult {
        if (email.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "The email can't be blank"
            )
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(
                successful = false,
                errorMessage = "That's not a valid email"
            )
        }
        return ValidationResult(
            successful = true,
            errorMessage = null
        )
    }
}