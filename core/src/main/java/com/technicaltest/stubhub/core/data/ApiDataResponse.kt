package com.technicaltest.stubhub.core.data


data class ApiDataResponse<T>(
    val results: List<T>
)
