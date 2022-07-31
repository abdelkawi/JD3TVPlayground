package com.genwin.jd3tv.screens.home.data

import com.genwin.jd3tv.screens.home.domain.entity.HomeSection
import com.genwin.jd3tv.screens.home.domain.entity.SectionType
import com.genwin.jd3tv.screens.home.domain.entity.SectionType.Card
import com.genwin.jd3tv.screens.home.domain.entity.SectionType.CardWithTitle
import com.genwin.jd3tv.screens.home.domain.entity.SectionType.Contest
import com.genwin.jd3tv.screens.home.domain.entity.SectionType.FaithItem
import com.genwin.jd3tv.screens.home.domain.entity.SectionType.FullItem
import com.genwin.jd3tv.screens.home.domain.entity.SectionType.Host
import com.genwin.jd3tv.screens.home.domain.entity.SectionType.Shop
import com.genwin.jd3tv.screens.home.domain.entity.SectionType.ViewPager


fun getHomeData(sections: List<HomeSection>): List<HomeSection> {
  val fullSections = mutableListOf<HomeSection>()
  fullSections.add(
    setHomeData(
      "", FullItem
    )
  )
  fullSections.add(
    setHomeData(
      "Popular movies", Contest)
  )
  fullSections.add(
    setHomeData(
      "Featured Shows", Contest
    )
  )
  fullSections.add(
    setHomeData(
      "JD3 TV Picks", ViewPager
    )
  )
  fullSections.add(
    setHomeData(
      "", FullItem
    )
  )
  fullSections.add(
    setHomeData(
      "New on JD3 TV", Contest
    )
  )
  fullSections.add(
    setHomeData(
      "Podcasts", CardWithTitle
    )
  )
  fullSections.add(
    setHomeData(
      "Exclusive NFT", Card
    )
  )
  fullSections.add(
    setHomeData(
      "Documentaries", Contest
    )
  )

  fullSections.add(
    setHomeData(
      "Sports Entertainment", Contest
    )
  )
  fullSections.add(
    setHomeData(
      "Upcoming Events", ViewPager
    )
  )
  fullSections.add(
    setHomeData(
      "JD3 TV Originals", Contest
    )
  )
  fullSections.add(
    setHomeData(
      "Faith", FaithItem
    )
  )

  fullSections.add(
    setHomeData(
      "Masterclass", ViewPager
    )
  )
  fullSections.add(
    setHomeData(
      "Shop", Shop
    )
  )
  fullSections.add(
    setHomeData(
      "TV Series", Contest
    )
  )
  fullSections.add(
    setHomeData(
      "Talk shows", Contest
    )
  )
  fullSections.add(
    setHomeData(
      "Not to miss", ViewPager
    )
  )
  fullSections.add(
    setHomeData(
      "Hosts", Host
    )
  )
  fullSections.forEach {
    it.setItems(sections[0].getItems())
  }
  return fullSections
}

fun setHomeData(title:String,type: SectionType):HomeSection =
  HomeSection(
    title, type, "", ItemDetailsRequest(
      emptyList(), "", "", emptyList()
    )
  )
