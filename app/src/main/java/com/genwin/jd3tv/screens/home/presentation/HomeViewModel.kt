package com.genwin.jd3tv.screens.home.presentation

import androidx.lifecycle.ViewModel
import com.genwin.jd3tv.screens.home.domain.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository) :ViewModel() {
  suspend fun getHomeDetails() = homeRepository.getHomeDetails("61ddcff8097ac672b880c35e","623acdd8ddab51a90f79f160")
}