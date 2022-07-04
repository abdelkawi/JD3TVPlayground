package com.genwin.jd3tv.screens.home.domain

import com.genwin.jd3tv.screens.home.data.DataItem
import com.genwin.jd3tv.screens.home.data.SectionsItem
import com.genwin.jd3tv.screens.home.domain.entity.HomeSection
fun SectionsItem.toHomeSection():HomeSection{
  return HomeSection(
    title =  this.ref?:"",
    type = HomeSection.getRandomType(),
    endpoint = this.content?.query?.endpoint?:"",
    items = this.content?.custom?.cards?.mapNotNull { it }
  )
}