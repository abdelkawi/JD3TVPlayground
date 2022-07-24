package com.genwin.jd3tv.screens.login.domain.interfaces

import com.genwin.jd3tv.common.Result
import com.genwin.jd3tv.screens.login.data.LoginResponse

interface LoginRepository {
    suspend fun login(
        username: String,
        pass: String
    ): Result<LoginResponse>
}