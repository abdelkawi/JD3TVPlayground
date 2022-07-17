package com.genwin.jd3tv.screens.login.presentation

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.genwin.jd3tv.R
import com.genwin.jd3tv.common.Result.Error
import com.genwin.jd3tv.common.Result.Success
import com.genwin.jd3tv.common.SharedPreference
import com.genwin.jd3tv.screens.home.presentation.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.launch
import javax.inject.Inject

//
// Created by Dina Mounib on 7/4/22.
//
@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModels()
    @Inject
    lateinit var sharedPreference: SharedPreference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setTextWatchers()
        lifecycleScope.launch {
            viewModel.validationEvents.collect { event ->
                when (event) {
                    is LoginViewModel.ValidationEvent.Success -> {
                        login()
                    }
                }
            }
        }
    }

    private fun login() {
        lifecycleScope.launch {
            val res =
                viewModel.login(emailET.text.toString(), passwordET.text.toString())
            when (res) {
                is Success -> {
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    if (rememberMeCB.isChecked) {
                        sharedPreference.setUserName(emailET.text.toString())
                        sharedPreference.setPassword(passwordET.text.toString())
                    }
                    Toast.makeText(
                        baseContext,
                        "Login successful",
                        Toast.LENGTH_LONG
                    ).show()
                }
                is Error -> {
                    Toast.makeText(
                        baseContext,
                        res.error.toString(),
                        Toast.LENGTH_LONG
                    ).show()
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

        signInBtn.setOnClickListener {
            viewModel.onEvent(LoginFormEvent.Submit)
            if (viewModel.state.emailError != null)
                emailET.error = viewModel.state.emailError
            else emailET.error = null
            if (viewModel.state.passwordError != null)
                passwordET.error = viewModel.state.passwordError
            else passwordET.error = null


        }
    }
}