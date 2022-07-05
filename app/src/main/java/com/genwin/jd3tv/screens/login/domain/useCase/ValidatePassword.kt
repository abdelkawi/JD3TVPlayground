package com.genwin.jd3tv.screens.login.domain.useCase

//
// Created by Dina Mounib on 7/4/22.
//
class ValidatePassword {
    fun execute(password: String): ValidationResult {
        if (password.length < 6) {
            return ValidationResult(
                successful = false,
                errorMessage = "The password needs to consist of at least 6 characters"
            )
        }
//        val containsLettersAndDigits = password.any { it.isDigit() } &&
//                password.any { it.isLetter() }
//        if (!containsLettersAndDigits) {
//            return ValidationResult(
//                successful = false,
//                errorMessage = "The password needs to contain at least one letter and digit"
//            )
//        }
        return ValidationResult(
            successful = true,
            errorMessage = null
        )
    }
}