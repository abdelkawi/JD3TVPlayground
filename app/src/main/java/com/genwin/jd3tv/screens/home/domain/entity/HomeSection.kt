package com.genwin.jd3tv.screens.home.domain.entity

import com.genwin.jd3tv.screens.home.domain.entity.SectionType.Card
import com.genwin.jd3tv.screens.home.domain.entity.SectionType.CardWithTitle
import com.genwin.jd3tv.screens.home.domain.entity.SectionType.Contest
import com.genwin.jd3tv.screens.home.domain.entity.SectionType.ViewPager
import kotlin.random.Random


open class HomeSection(val title:String, val type:SectionType,val endpoint:String ,val items:List<String>?){
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



enum class SectionType{
  Card,ViewPager,CardWithTitle,Contest
}