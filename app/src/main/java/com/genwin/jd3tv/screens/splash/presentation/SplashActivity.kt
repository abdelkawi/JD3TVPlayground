package com.genwin.jd3tv.screens.splash.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.genwin.jd3tv.R
import com.genwin.jd3tv.common.Result
import com.genwin.jd3tv.common.SharedPreference
import com.genwin.jd3tv.screens.home.presentation.MainActivity
import com.genwin.jd3tv.screens.login.presentation.LoginActivity
import com.genwin.jd3tv.screens.login.presentation.LoginViewModel
import com.genwin.jd3tv.screens.welcome.presentation.WelcomeActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject


//
// Created by Dina Mounib on 6/28/22.
//
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private val viewModel: SplashViewModel by viewModels()
    private val loginViewModel: LoginViewModel by viewModels()

    @Inject
    lateinit var sharedPreference: SharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if (sharedPreference.getUserName().isNotEmpty()) {
            login(sharedPreference.getUserName(), sharedPreference.getPassword())
        }else
            startActivity(Intent(this@SplashActivity, WelcomeActivity::class.java))
    }

    private fun login(userName: String, password: String) {
        lifecycleScope.launch {
            val res =
                loginViewModel.login(userName, password)
            when (res) {
                is Result.Success -> {
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    this@SplashActivity.finish()
                }
                is Result.Error -> {
                    Toast.makeText(
                        baseContext,
                        res.error.toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

}