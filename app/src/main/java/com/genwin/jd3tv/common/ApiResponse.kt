package com.genwin.jd3tv.common

data class ApiResponse<T>(
     val data: T?,
     val errors:List<String>?
)