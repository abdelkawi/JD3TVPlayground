package com.genwin.jd3tv.common

import com.genwin.jd3tv.common.Result.Success
import com.genwin.jd3tv.screens.splash.data.SplashResponse
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Response


suspend inline fun <reified T> safeApiCall(
  noinline call: suspend () -> Response<ApiResponse<T>>
): Result<T> {
  return try {
    val res = call()
    return when (res.isSuccessful) {
      true -> {
        if(res.body()?.data==null)
          Success(
           SplashResponse(id = res.body()?._id?:"", themeData = res.body()?.theme_data) as T
          )
        else
        Success(
          res.body()?.data as T
        )
      }
      false -> {
        Result.Error(
          res.errorBody()?.toError()!!.message
        )
      }
    }
  } catch (e: Exception) {
    Result.Error(e.toString())
  }
}

inline fun ResponseBody.toError(): Error {
  return Gson().fromJson(this.charStream(), Error::class.java)
}