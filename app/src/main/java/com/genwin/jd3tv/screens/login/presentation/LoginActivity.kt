package com.genwin.jd3tv.screens.login.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
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
        loginPB.visibility = View.VISIBLE
        signInBtn.visibility = View.GONE
        lifecycleScope.launch {
            val res =
                viewModel.login(emailET.text.toString(), passwordET.text.toString())
            when (res) {
                is Success -> {

                    loginPB.visibility = View.GONE
                    signInBtn.visibility = View.VISIBLE
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    if (rememberMeCB.isChecked) {
                        sharedPreference.setUserName(emailET.text.toString())
                        sharedPreference.setPassword(passwordET.text.toString())
                        sharedPreference.setEmail(res.data.email ?: "")
                        sharedPreference.setPhoto(res.data.photo ?: "")
                        sharedPreference.setFullName(res.data.firstName + res.data.lastName)
                        sharedPreference.setNickName(
                            res.data.firstName?.substring(
                                0,
                                1
                            ) + res.data.lastName?.substring(0, 1)
                        )
                    }
                }
                is Error -> {

                    loginPB.visibility = View.GONE
                    signInBtn.visibility = View.VISIBLE
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
        emailET.onFocusChangeListener = View.OnFocusChangeListener { view, b ->
            if (b)
                emailLayoutTextInput.setStartIconTintList(
                    ContextCompat.getColorStateList(
                        this@LoginActivity,
                        R.color.medium_purple
                    )
                )
            else
                emailLayoutTextInput.setStartIconTintList(
                    ContextCompat.getColorStateList(
                        this@LoginActivity,
                        R.color.white
                    )
                )
        }
        passwordET.onFocusChangeListener = View.OnFocusChangeListener { view, b ->
            if (b) {
                setPasswordColor(R.color.medium_purple)
            } else {
               setPasswordColor(R.color.white)
            }
        }
        passwordET.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                passwordET.clearFocus()
                emailET.clearFocus()
                val imm: InputMethodManager =
                    v.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v.windowToken, 0)
                true
            } else {
                false
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


        }
    }

    private fun setPasswordColor(color:Int)
    {
        passwordLayoutTextInput.setStartIconTintList(
            ContextCompat.getColorStateList(
                this@LoginActivity,
                color
            )
        )
        passwordLayoutTextInput.setEndIconTintList(
            ContextCompat.getColorStateList(
                this@LoginActivity,
                color
            )
        )
    }
}