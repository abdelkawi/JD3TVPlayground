package com.example.jd3tvplayground.domain.common



/**
 * Authored by Mohamed Fathy on 30 Nov, 2019.
 * Contact: m.fathy@rytalo.com
 * by Rytalo.
 */
sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val error: String?) : Result<Nothing>()
}
