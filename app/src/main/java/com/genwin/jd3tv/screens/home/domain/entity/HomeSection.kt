package com.genwin.jd3tv.screens.home.domain.entity

import com.genwin.jd3tv.screens.home.data.ItemDetailsRequest
import com.genwin.jd3tv.screens.home.data.ItemDetailsResponse
import com.genwin.jd3tv.screens.home.domain.entity.SectionType.Card
import com.genwin.jd3tv.screens.home.domain.entity.SectionType.CardWithTitle
import com.genwin.jd3tv.screens.home.domain.entity.SectionType.Contest
import com.genwin.jd3tv.screens.home.domain.entity.SectionType.ViewPager
import kotlin.random.Random


 class HomeSection(
  val title: String,
  val type: SectionType,
  val endpoint: String,
  val dataRequest:ItemDetailsRequest
  ) {

  private var itemsList:List<ItemDetailsResponse> = emptyList()
  fun setItems(items:List<ItemDetailsResponse>){
    this.itemsList = items
  }
   fun getItems() = itemsList
  companion object {
    fun getRandomType(): SectionType {
      return when (Random.nextInt(1, 4)) {
        1 -> ViewPager
        2 -> CardWithTitle
        3 -> Contest
        else -> Card
      }
    }
  }
}


enum class SectionType {
  Card, ViewPager, CardWithTitle, Contest
}