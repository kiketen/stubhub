package com.technicaltest.details.data

import com.google.gson.annotations.SerializedName
import com.technicaltest.details.domain.CharacterApparition
import com.technicaltest.stubhub.core.data.CharacterThumbnail


data class CharacterApparitionApiModel(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("thumbnail") val thumbnail: CharacterThumbnail
)

fun CharacterApparitionApiModel.toDomain() = CharacterApparition(
    id = id,
    title = title,
    image = "${thumbnail.path}.${thumbnail.extension}"
)