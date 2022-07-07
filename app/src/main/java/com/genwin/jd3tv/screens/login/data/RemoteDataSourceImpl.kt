package com.genwin.jd3tv.screens.login.data

import com.genwin.jd3tv.common.Result
import com.genwin.jd3tv.common.safeApiCall
import com.genwin.jd3tv.screens.login.domain.interfaces.RemoteDataSource
import com.genwin.jd3tv.screens.login.domain.interfaces.api.LoginApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import javax.inject.Inject

//
// Created by Dina Mounib on 7/5/22.
//
class RemoteDataSourceImpl @Inject constructor(private val loginApi: LoginApi) : RemoteDataSource {
    override suspend fun login(email: String, password: String): Result<LoginResponse> =
        withContext(Dispatchers.IO) {
            safeApiCall {
                val map = mutableMapOf<String, String>()
                map.put("username", email)
                map.put("password", password)
                loginApi.login(map)
            }
        }


}