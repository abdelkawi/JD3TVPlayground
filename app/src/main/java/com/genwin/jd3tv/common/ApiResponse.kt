package com.genwin.jd3tv.common

import com.genwin.jd3tv.screens.home.data.Pagination
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiResponse<T>(
     val data: T,
     val pagination: Pagination?,
     val message:String?,
     val errors :Map<String,List<String>>?
)