package com.example.jd3tvplayground.domain.common

data class ApiResponse<T>(
     val data: T?,
     val errors:List<String>?
)