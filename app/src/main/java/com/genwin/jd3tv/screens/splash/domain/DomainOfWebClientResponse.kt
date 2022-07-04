package com.genwin.jd3tv.screens.splash.domain.home

import com.google.gson.annotations.SerializedName

//
// Created by Dina Mounib on 6/28/22.
//
data class DomainOfWebClientResponse(
    @SerializedName("_id")
    val client_id: String? = null,
    @SerializedName("theme_data")
    val theme: themeObject? = null
)

data class themeObject(
    @SerializedName("_id")
    val theme_id: String? = null
)