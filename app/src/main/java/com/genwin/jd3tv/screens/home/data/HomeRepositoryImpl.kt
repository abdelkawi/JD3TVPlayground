package com.genwin.jd3tv.screens.home.data

import com.genwin.jd3tv.common.Result
import com.genwin.jd3tv.common.Result.Error
import com.genwin.jd3tv.common.Result.Success
import com.genwin.jd3tv.screens.home.domain.interfaces.HomeRepository
import com.genwin.jd3tv.screens.home.domain.interfaces.RemoteDataSource
import com.genwin.jd3tv.screens.home.domain.entity.HomeSection
import com.genwin.jd3tv.screens.home.domain.entity.SectionType.Card
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) : HomeRepository {
  override suspend fun getHomeDetails(clientId:String,themeId:String): Result<List<HomeSection>> {
    val res = remoteDataSource.getHomeData(clientId,themeId)
    return  when(res){
      is Error -> Error(res.error)
      is Success -> Success(listOf<HomeSection>(HomeSection(res.data[0].sections?.get(0)?.ref?:"",Card)))
    }
  }
}