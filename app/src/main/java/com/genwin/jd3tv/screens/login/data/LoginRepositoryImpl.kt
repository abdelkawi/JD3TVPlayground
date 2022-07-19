package com.genwin.jd3tv.screens.login.data

import com.genwin.jd3tv.common.Result
import com.genwin.jd3tv.screens.login.domain.interfaces.RemoteDataSource
import com.genwin.jd3tv.screens.login.domain.interfaces.LoginRepository
import okhttp3.ResponseBody
import javax.inject.Inject

//
// Created by Dina Mounib on 7/5/22.
//
class LoginRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    LoginRepository {
    override suspend fun login(username: String, pass: String): Result<LoginResponse> {
        val res = remoteDataSource.login(username, pass)
        return when (res) {
            is Result.Error -> Result.Error(res.error)
            is Result.Success -> Result.Success(res.data)
        }
    }

}