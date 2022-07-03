package com.genwin.jd3tv.screens.home.data

import com.genwin.jd3tv.common.Result

import com.genwin.jd3tv.common.safeApiCall
import com.genwin.jd3tv.screens.home.domain.HomeApiService
import com.genwin.jd3tv.screens.home.domain.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val homeApiService: HomeApiService) :RemoteDataSource {
  override suspend fun getHomeData(clientId:String,themeId:String): Result<HomeResponse> =
    withContext(Dispatchers.IO) {
      safeApiCall {
       homeApiService.getHomeDetails("home", "", "")
      }
    }
}