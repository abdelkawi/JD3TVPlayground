package com.genwin.jd3tv.screens.login.domain.useCase

//
// Created by Dina Mounib on 7/4/22.
//
class ValidateTerms {

    fun execute(acceptedTerms: Boolean): ValidationResult {
        if(!acceptedTerms) {
            return ValidationResult(
                successful = false,
                errorMessage = "Please accept the terms"
            )
        }
        return ValidationResult(
            successful = true,
            errorMessage = null
        )
    }
}