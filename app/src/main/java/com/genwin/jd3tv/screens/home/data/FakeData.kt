package com.genwin.jd3tv.screens.home.data

import com.genwin.jd3tv.screens.home.domain.entity.HomeSection
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
//        var sections = mutableListOf<ItemDetailsRequest>()
//        sections.add(ItemDetailsRequest(emptyList(),"","", emptyList()))
//        sections.add(ItemDetailsRequest(emptyList(),"","", emptyList()))
//        sections.add(ItemDetailsRequest(emptyList(),"","", emptyList()))
//        sections.add(ItemDetailsRequest(emptyList(),"","", emptyList()))
//        sections.add(ItemDetailsRequest(emptyList(),"","", emptyList()))

  fullSections.add(
    HomeSection(
      "Popular movies", Contest, "", ItemDetailsRequest(
        emptyList(), "", "", emptyList()
      )
    )
  )
  fullSections.add(
    HomeSection(
      "Featured Shows", Contest, "", ItemDetailsRequest(
        emptyList(), "", "", emptyList()
      )
    )
  )
  fullSections.add(
    HomeSection(
      "JD3 TV Picks", ViewPager, "", ItemDetailsRequest(
        emptyList(), "", "", emptyList()
      )
    )
  )
  fullSections.add(
    HomeSection(
      "", FullItem, "", ItemDetailsRequest(
        emptyList(), "", "", emptyList()
      )
    )
  )
  fullSections.add(
    HomeSection(
      "New on JD3 TV", Contest, "", ItemDetailsRequest(
        emptyList(), "", "", emptyList()
      )
    )
  )
  fullSections.add(
    HomeSection(
      "Podcasts", CardWithTitle, "", ItemDetailsRequest(
        emptyList(), "", "", emptyList()
      )
    )
  )
  fullSections.add(
    HomeSection(
      "Exclusive NFT", Card, "", ItemDetailsRequest(
        emptyList(), "", "", emptyList()
      )
    )
  )
  fullSections.add(
    HomeSection(
      "Documentaries", Contest, "", ItemDetailsRequest(
        emptyList(), "", "", emptyList()
      )
    )
  )

  fullSections.add(
    HomeSection(
      "Sports Entertainment", Contest, "", ItemDetailsRequest(
        emptyList(), "", "", emptyList()
      )
    )
  )
  fullSections.add(
    HomeSection(
      "Upcoming Events", ViewPager, "", ItemDetailsRequest(
        emptyList(), "", "", emptyList()
      )
    )
  )
  fullSections.add(
    HomeSection(
      "JD3 TV Originals", Contest, "", ItemDetailsRequest(
        emptyList(), "", "", emptyList()
      )
    )
  )
  fullSections.add(
    HomeSection(
      "Faith", FaithItem, "", ItemDetailsRequest(
        emptyList(), "", "", emptyList()
      )
    )
  )

  fullSections.add(
    HomeSection(
      "Masterclass", ViewPager, "", ItemDetailsRequest(
        emptyList(), "", "", emptyList()
      )
    )
  )
  fullSections.add(
    HomeSection(
      "Shop", Shop, "", ItemDetailsRequest(
        emptyList(), "", "", emptyList()
      )
    )
  )
  fullSections.add(
    HomeSection(
      "TV Series", Contest, "", ItemDetailsRequest(
        emptyList(), "", "", emptyList()
      )
    )
  )
  fullSections.add(
    HomeSection(
      "Talk shows", Contest, "", ItemDetailsRequest(
        emptyList(), "", "", emptyList()
      )
    )
  )
  fullSections.add(
    HomeSection(
      "Not to miss", ViewPager, "", ItemDetailsRequest(
        emptyList(), "", "", emptyList()
      )
    )
  )
  fullSections.add(
    HomeSection(
      "Hosts", Host, "", ItemDetailsRequest(
        emptyList(), "", "", emptyList()
      )
    )
  )
  fullSections.forEach {
    it.setItems(sections[0].getItems())
  }
  return fullSections
}