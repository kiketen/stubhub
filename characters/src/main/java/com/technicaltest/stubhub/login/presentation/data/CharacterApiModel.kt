package com.technicaltest.stubhub.login.presentation.data

import com.google.gson.annotations.SerializedName
import com.technicaltest.stubhub.login.presentation.domain.MarvelCharacter


data class CharacterApiModel(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("thumbnail") val thumbnail: CharacterThumbnail
)

fun CharacterApiModel.toDomain() = MarvelCharacter(
    id = id,
    name = name,
    image = "${thumbnail.path}.${thumbnail.extension}"
)