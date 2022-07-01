package com.genwin.jd3tv.domain

data class ApiResponse<T>(
     val data: T?,
     val errors:List<String>?
)