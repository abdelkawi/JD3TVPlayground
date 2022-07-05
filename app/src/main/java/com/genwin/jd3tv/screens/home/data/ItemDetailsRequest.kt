package com.genwin.jd3tv.screens.home.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ItemDetailsRequest(
  val type: List<String?>? = null,
  val status: String?,
  val select:String?,
  val experiences_ids:List<String?>?
)