package com.genwin.jd3tv.screens.login.domain.interfaces

import com.genwin.jd3tv.common.Result
import com.genwin.jd3tv.screens.login.data.LoginResponse

interface RemoteDataSource {
    suspend fun login(email: String, password: String): Result<LoginResponse>
}