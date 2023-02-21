package com.technicaltest.stubhub.login.presentation.presentation


sealed class CharactersError {
    object Unknown : CharactersError()
    object CharactersNotFound : CharactersError()
}
