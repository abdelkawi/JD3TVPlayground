package com.genwin.jd3tv.screens.login.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.genwin.jd3tv.R
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.launch

//
// Created by Dina Mounib on 7/4/22.
//
class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setTextWatchers()
        lifecycleScope.launch {
            viewModel.validationEvents.collect { event ->
                when (event) {
                    is LoginViewModel.ValidationEvent.Success -> {
                        Toast.makeText(
                            baseContext,
                            "Login successful",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }

    private fun setTextWatchers() {
        val emailTW = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.onEvent(LoginFormEvent.EmailChanged(s.toString()))
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        }
        val passwordTW = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.onEvent(LoginFormEvent.PasswordChanged(s.toString()))
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        }
        emailET.addTextChangedListener(emailTW)
        passwordET.addTextChangedListener(passwordTW)
        rememberMeCB.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                viewModel.onEvent(LoginFormEvent.AcceptTerms(isChecked))
            }
        }
        signInBtn.setOnClickListener {
            viewModel.onEvent(LoginFormEvent.Submit)
            if (viewModel.state.emailError != null)
                emailET.error = viewModel.state.emailError
            else emailET.error = null
            if (viewModel.state.passwordError != null)
                passwordET.error = viewModel.state.passwordError
            else passwordET.error = null
            if (viewModel.state.termsError != null)
                rememberMeCB.error = viewModel.state.termsError
            else rememberMeCB.error = null

        }
    }
}