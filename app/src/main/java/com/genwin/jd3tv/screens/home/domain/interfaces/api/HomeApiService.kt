package com.genwin.jd3tv.screens.home.domain.interfaces.api

import com.genwin.jd3tv.common.ApiResponse
import com.genwin.jd3tv.screens.home.data.DataItem
import com.genwin.jd3tv.screens.home.data.ItemDetailsRequest
import com.genwin.jd3tv.screens.home.data.ItemDetailsResponse
import retrofit2.Response
import retrofit2.http.*

interface HomeApiService {

  @GET("webclients/app-api/pages")
  suspend fun getHomeDetails(
    @Query("page_ref") pagerRef: String,
    @Query("theme_id") themeId: String,
    @Query("webclient_id") webclientId: String
  ): Response<ApiResponse<List<DataItem>>>

  @POST
  suspend fun getSectionItems(
    @Url endpoint:String,
    @Body request: ItemDetailsRequest
  ):Response<ApiResponse<List<ItemDetailsResponse>>>

}