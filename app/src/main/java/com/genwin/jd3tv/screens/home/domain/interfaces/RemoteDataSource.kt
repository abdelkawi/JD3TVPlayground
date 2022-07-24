package com.genwin.jd3tv.screens.home.domain.interfaces

import com.genwin.jd3tv.common.Result
import com.genwin.jd3tv.screens.home.data.DataItem
import com.genwin.jd3tv.screens.home.data.ItemDetailsRequest
import com.genwin.jd3tv.screens.home.data.ItemDetailsResponse

interface RemoteDataSource {
  suspend fun getHomeData(pageRef:String,clientId: String, themeId: String): Result<List<DataItem>>
  suspend fun getSectionItems(endpoint:String,itemDetailsRequest: ItemDetailsRequest): Result<List<ItemDetailsResponse>>
}