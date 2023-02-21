package com.technicaltest.stubhub.core.data

import com.google.gson.annotations.SerializedName


data class ApiResponse<T>(
    @SerializedName("data") val data: ApiDataResponse<T>
)
