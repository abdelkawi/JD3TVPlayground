package com.genwin.jd3tv.screens.home.data

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
data class HomeResponse(

	@Json(name="pagination")
	val pagination: Pagination? = null,

	@Json(name="data")
	val data: List<DataItem?>? = null
)

@JsonClass(generateAdapter = true)
data class DataItem(

	@Json(name="ref")
	val ref: String? = null,

	@Json(name="updated_at")
	val updatedAt: String? = null,

	@Json(name="webclient_id")
	val webclientId: String? = null,

	@Json(name="theme_id")
	val themeId: String? = null,

	@Json(name="active")
	val active: String? = null,

	@Json(name="created_at")
	val createdAt: String? = null,

	@Json(name="_id")
	val id: String? = null,

	@Json(name="seo")
	val seo: Seo? = null,

	@Json(name="is_default")
	val isDefault: String? = null,

	@Json(name="sections")
	val sections: List<SectionsItem?>? = null
)

@JsonClass(generateAdapter = true)
data class Custom(

	@Json(name="show")
	val show: Boolean? = null,

	@Json(name="cards")
	val cards: List<String?>? = null,

	@Json(name="title")
	val title: String? = null
)

@JsonClass(generateAdapter = true)
data class Content(

	@Json(name="query")
	val query: Query? = null,

	@Json(name="custom")
	val custom: Custom? = null,

	@Json(name="show")
	val show: Boolean? = null,

	@Json(name="type")
	val type: String? = null
)

@JsonClass(generateAdapter = true)
data class Pagination(

	@Json(name="per_page")
	val perPage: Int? = null,

	@Json(name="total")
	val total: Int? = null,

	@Json(name="to")
	val to: Int? = null,

	@Json(name="current_page")
	val currentPage: Int? = null
)

@JsonClass(generateAdapter = true)
data class Query(

	@Json(name="endpoint")
	val endpoint: String? = null,

	@Json(name="select")
	val select: String? = null,

	@Json(name="criteria")
	val criteria: Criteria? = null,

	@Json(name="sort")
	val sort: Sort? = null,

	@Json(name="source")
	val source: String? = null,

	@Json(name="experiences_ids")
	val experiencesIds: List<String?>? = null
)

@JsonClass(generateAdapter = true)
data class Criteria(

	@Json(name="type")
	val type: List<String?>? = null,

	@Json(name="status")
	val status: String? = null
)

@JsonClass(generateAdapter = true)
data class Sort(

	@Json(name="created_at")
	val createdAt: Int? = null
)

@JsonClass(generateAdapter = true)
data class Seo(

	@Json(name="showInNav")
	val showInNav: Boolean? = null,

	@Json(name="show")
	val show: Boolean? = null,

	@Json(name="description")
	val description: String? = null,

	@Json(name="title")
	val title: String? = null
)

@JsonClass(generateAdapter = true)
data class SectionsItem(

	@Json(name="page_id")
	val pageId: String? = null,

	@Json(name="ref")
	val ref: String? = null,

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

	@Json(name="content")
	val content: Content? = null
)
