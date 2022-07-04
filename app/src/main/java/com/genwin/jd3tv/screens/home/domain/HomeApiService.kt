package com.genwin.jd3tv.screens.home.domain

import com.genwin.jd3tv.common.ApiResponse
import com.genwin.jd3tv.screens.home.data.HomeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeApiService {
  @GET("pages")
  suspend fun getHomeDetails(
    @Query("page_ref") pagerRef:String,
    @Query("theme_id") themeId:String,
    @Query("webclient_id") webclientId:String)
  :Response<ApiResponse<HomeResponse>>
}