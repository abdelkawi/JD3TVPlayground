package com.genwin.jd3tv.screens.home.data

import com.genwin.jd3tv.common.Result
import com.genwin.jd3tv.common.safeApiCall
import com.genwin.jd3tv.screens.home.domain.interfaces.RemoteDataSource
import com.genwin.jd3tv.screens.home.domain.interfaces.api.HomeApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val homeApiService: HomeApiService) : RemoteDataSource {
  override suspend fun getHomeData(pageRef:String,clientId:String,themeId:String): Result<List<DataItem>> =
    withContext(Dispatchers.IO) {
      safeApiCall {
       homeApiService.getHomeDetails(pageRef, themeId, clientId)
      }
    }

  override suspend fun getSectionItems(endpoint:String,itemDetailsRequest: ItemDetailsRequest):
      Result<List<ItemDetailsResponse>> =
    withContext(Dispatchers.IO){
      safeApiCall {
        homeApiService.getSectionItems(endpoint,itemDetailsRequest)
      }
    }
}