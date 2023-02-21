package com.technicaltest.stubhub.presentation


sealed class CharactersError {
    object Unknown : CharactersError()
    object CharactersNotFound : CharactersError()
}
