package com.genwin.jd3tv.screens.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.genwin.jd3tv.screens.login.domain.useCase.ValidateEmail
import com.genwin.jd3tv.screens.login.domain.useCase.ValidatePassword
import com.genwin.jd3tv.screens.login.domain.useCase.ValidateTerms
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

//
// Created by Dina Mounib on 7/4/22.
//
@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {
    var state = LoginFormState()
    private val validateEmail: ValidateEmail = ValidateEmail()
    private val validatePassword: ValidatePassword = ValidatePassword()
    private val validateTerms: ValidateTerms = ValidateTerms()

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: LoginFormEvent) {
        when (event) {
            is LoginFormEvent.EmailChanged -> {
                state = state.copy(email = event.email)
            }
            is LoginFormEvent.PasswordChanged -> {
                state = state.copy(password = event.password)
            }
            is LoginFormEvent.AcceptTerms -> {
                state = state.copy(acceptedTerms = event.isAccepted)
            }
            is LoginFormEvent.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        val emailResult = validateEmail.execute(state.email)
        val passwordResult = validatePassword.execute(state.password)
        val termsResult = validateTerms.execute(state.acceptedTerms)

        state = state.copy(
            emailError = emailResult.errorMessage,
            passwordError = passwordResult.errorMessage,
            termsError = termsResult.errorMessage
        )

        val hasError = listOf(
            emailResult,
            passwordResult,
            termsResult
        ).any { !it.successful }

        if (hasError)
            return

        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)
        }
    }

    sealed class ValidationEvent {
        object Success : ValidationEvent()
    }
}