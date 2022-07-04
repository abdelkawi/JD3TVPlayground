package com.genwin.jd3tv.screens.home.domain.interfaces

import com.genwin.jd3tv.common.Result
import com.genwin.jd3tv.screens.home.data.DataItem

interface RemoteDataSource {
  suspend fun getHomeData(clientId: String, themeId: String): Result<List<DataItem>>
}