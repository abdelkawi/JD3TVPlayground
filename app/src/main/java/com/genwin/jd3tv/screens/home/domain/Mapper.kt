package com.genwin.jd3tv.screens.home.domain

import com.genwin.jd3tv.screens.home.data.ItemDetailsRequest
import com.genwin.jd3tv.screens.home.data.SectionsItem
import com.genwin.jd3tv.screens.home.domain.entity.HomeSection
import com.genwin.jd3tv.screens.home.domain.entity.SectionType

fun SectionsItem.toHomeSection(): HomeSection {

    return HomeSection(
        title = this.ref ?: "",
        type = SectionType.Contest ,//HomeSection.getRandomType(),
        endpoint = this.content?.query?.endpoint ?: "",
        dataRequest = ItemDetailsRequest(
            type = this.content?.query?.criteria?.type,
            status = this.content?.query?.criteria?.status,
            select = "main_photo image title name created_at summary type alias _id experience_id",
            experiences_ids = this.content?.query?.experiencesIds
        )
    )
}