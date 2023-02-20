package com.technicaltest.stubhub.login.presentation.data

import com.google.gson.annotations.SerializedName


data class CharacterThumbnail(
    @SerializedName("path") val path: String,
    @SerializedName("extension") val extension: String
)
