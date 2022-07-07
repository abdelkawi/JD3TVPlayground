package com.genwin.jd3tv.screens.home.presentation

import androidx.lifecycle.ViewModel
import com.genwin.jd3tv.screens.home.data.ItemDetailsRequest
import com.genwin.jd3tv.screens.home.domain.entity.HomeSection
import com.genwin.jd3tv.screens.home.domain.interfaces.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository) : ViewModel() {
  suspend fun getHomeDetails(pageRef:String,clientId:String,themeId:String) =
    homeRepository.getHomeDetails(
      clientId,
      themeId
    )

  suspend fun getSectionItems(endpoint:String,itemDetailsRequest: ItemDetailsRequest) =
    homeRepository.getSectionItems(endpoint,itemDetailsRequest)
}