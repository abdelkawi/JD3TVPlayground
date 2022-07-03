package com.genwin.jd3tv.screens.home.domain

import com.genwin.jd3tv.common.Result
import com.genwin.jd3tv.screens.home.domain.entity.HomeSection

interface HomeRepository {
  suspend fun getHomeDetails(clientId:String,themeId:String):Result<List<HomeSection>>
}