package com.technicaltest.stubhub.core.data

import com.google.gson.annotations.SerializedName


data class ApiDataResponse<T>(
    @SerializedName("total") val total: Int,
    @SerializedName("results") val results: List<T>
)
