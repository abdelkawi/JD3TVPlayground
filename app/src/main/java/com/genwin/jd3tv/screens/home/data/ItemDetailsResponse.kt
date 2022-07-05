package com.genwin.jd3tv.screens.home.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ItemDetailsResponse(

  @Json(name = "experience_id")
  val experienceId: String? = null,

  @Json(name = "summary")
  val summary: String? = null,

  @Json(name = "fundraiser")
  val fundraiser: Fundraiser? = null,

  @Json(name = "organization")
  val organization: Organization? = null,

  @Json(name = "alias")
  val alias: String? = null,

  @Json(name = "created_at")
  val createdAt: String? = null,

  @Json(name = "_id")
  val id: String? = null,

  @Json(name = "title")
  val title: String? = null,

  @Json(name = "type")
  val type: String? = null,

  @Json(name = "main_photo")
  val mainPhoto: MainPhoto? = null
)

@JsonClass(generateAdapter = true)
data class MainPhoto(
  @Json(name = "file_url")
  val fileUrl: String? = null,

  @Json(name = "mime_type")
  val mimeType: String? = null,

  @Json(name = "originalname")
  val originalname: String? = null,

  @Json(name = "file_name")
  val fileName: String? = null
)

@JsonClass(generateAdapter = true)
data class Organization(

  @Json(name = "_id")
  val id: String? = null
)

@JsonClass(generateAdapter = true)
data class Fundraiser(

  @Json(name = "_id")
  val id: String? = null
)
