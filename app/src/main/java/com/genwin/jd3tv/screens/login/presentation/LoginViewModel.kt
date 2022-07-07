package com.genwin.jd3tv.screens.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.genwin.jd3tv.screens.login.domain.interfaces.LoginRepository
import com.genwin.jd3tv.screens.login.domain.useCase.ValidateEmail
import com.genwin.jd3tv.screens.login.domain.useCase.ValidatePassword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

//
// Created by Dina Mounib on 7/4/22.
//
@HiltViewModel
class LoginViewModel @Inject constructor(private val loginRepository: LoginRepository) :
    ViewModel() {
    var state = LoginFormState()
    private val validateEmail: ValidateEmail = ValidateEmail()
    private val validatePassword: ValidatePassword = ValidatePassword()

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
            is LoginFormEvent.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        val emailResult = validateEmail.execute(state.email)
        val passwordResult = validatePassword.execute(state.password)

        state = state.copy(
            emailError = emailResult.errorMessage,
            passwordError = passwordResult.errorMessage
        )

        val hasError = listOf(
            emailResult,
            passwordResult
        ).any { !it.successful }

        if (hasError)
            return

        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)
        }
    }

    suspend fun login(email: String, password: String) =
        loginRepository.login(username = email, pass = password)



    sealed class ValidationEvent {
        object Success : ValidationEvent()
    }
}