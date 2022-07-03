package com.genwin.jd3tv.screens.home.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HomeResponse(

	@field:SerializedName("pagination")
	val pagination: Pagination? = null,

	@field:SerializedName("data")
	val data: List<DataItem?>? = null
) : Parcelable

@Parcelize
data class Seo(

	@field:SerializedName("showInNav")
	val showInNav: Boolean? = null,

	@field:SerializedName("show")
	val show: Boolean? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("title")
	val title: String? = null
) : Parcelable

@Parcelize
data class Sort(

	@field:SerializedName("created_at")
	val createdAt: Int? = null
) : Parcelable

@Parcelize
data class Query(

	@field:SerializedName("endpoint")
	val endpoint: String? = null,

	@field:SerializedName("select")
	val select: String? = null,

	@field:SerializedName("criteria")
	val criteria: Criteria? = null,

	@field:SerializedName("sort")
	val sort: Sort? = null,

	@field:SerializedName("source")
	val source: String? = null,

	@field:SerializedName("experiences_ids")
	val experiencesIds: List<String?>? = null
) : Parcelable

@Parcelize
data class Pagination(

	@field:SerializedName("per_page")
	val perPage: Int? = null,

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("to")
	val to: Int? = null,

	@field:SerializedName("current_page")
	val currentPage: Int? = null
) : Parcelable

@Parcelize
data class Sections(

	@field:SerializedName("pagination")
	val pagination: Pagination? = null,

	@field:SerializedName("data")
	val data: List<DataItem?>? = null
) : Parcelable

@Parcelize
data class CardsItem(

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("url")
	val url: String? = null
) : Parcelable

@Parcelize
data class Custom(

	@field:SerializedName("show")
	val show: Boolean? = null,

	@field:SerializedName("cards")
	val cards: List<String?>? = null,

	@field:SerializedName("title")
	val title: String? = null
) : Parcelable

@Parcelize
data class Content(

	@field:SerializedName("query")
	val query: Query? = null,

	@field:SerializedName("custom")
	val custom: Custom? = null,

	@field:SerializedName("show")
	val show: Boolean? = null,

	@field:SerializedName("type")
	val type: String? = null
) : Parcelable

@Parcelize
data class Criteria(

	@field:SerializedName("type")
	val type: List<String?>? = null,

	@field:SerializedName("status")
	val status: String? = null
) : Parcelable

@Parcelize
data class DataItem(

	@field:SerializedName("ref")
	val ref: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("webclient_id")
	val webclientId: String? = null,

	@field:SerializedName("theme_id")
	val themeId: String? = null,

	@field:SerializedName("active")
	val active: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("seo")
	val seo: Seo? = null,

	@field:SerializedName("is_default")
	val isDefault: String? = null,

	@field:SerializedName("sections")
	val sections: Sections? = null,

	@field:SerializedName("page_id")
	val pageId: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("content")
	val content: Content? = null
) : Parcelable
