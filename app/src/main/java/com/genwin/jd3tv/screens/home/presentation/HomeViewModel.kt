package com.genwin.jd3tv.screens.home.presentation

import androidx.lifecycle.ViewModel
import com.genwin.jd3tv.screens.home.data.ItemDetailsRequest
import com.genwin.jd3tv.screens.home.domain.entity.HomeSection
import com.genwin.jd3tv.screens.home.domain.interfaces.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository) : ViewModel() {
  suspend fun getHomeDetails() =
    homeRepository.getHomeDetails(
      "61ddcff8097ac672b880c35e",
      "623acdd8ddab51a90f79f160"
    )

  suspend fun getSectionItems(endpoint:String,itemDetailsRequest: ItemDetailsRequest) =
    homeRepository.getSectionItems(endpoint,itemDetailsRequest)
}