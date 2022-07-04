package com.genwin.jd3tv.screens.splash.data
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//
// Created by Dina Mounib on 7/4/22.
//

@JsonClass(generateAdapter = true)
data class SplashResponse(
    @Json(name="theme_data")
    val themeData: ThemeData? = null,

    @Json(name="_id")
    val id: String? = null,

)

@JsonClass(generateAdapter = true)
data class ThemeData(
    @Json(name="ref")
    val ref: String? = null,

    @Json(name="pages")
    val pages: List<PagesItem?>? = null,

    @Json(name="updated_at")
    val updatedAt: String? = null,

    @Json(name="active")
    val active: String? = null,

    @Json(name="description")
    val description: String? = null,

    @Json(name="created_at")
    val createdAt: String? = null,

    @Json(name="_id")
    val id: String? = null,

    @Json(name="title")
    val title: String? = null
)

@JsonClass(generateAdapter = true)
data class PagesItem(

    @Json(name="ref")
    val ref: String? = null,

    @Json(name="description")
    val description: String? = null,

    @Json(name="sections")
    val sections: List<SectionsItem?>? = null
)
@JsonClass(generateAdapter = true)
data class SectionsItem(

    @Json(name="ref")
    val ref: String? = null,

    @Json(name="description")
    val description: String? = null
)