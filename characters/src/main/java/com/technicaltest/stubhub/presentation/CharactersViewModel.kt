package com.technicaltest.stubhub.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.technicaltest.stubhub.core.presentation.SingleLiveEvent
import com.technicaltest.stubhub.core.presentation.ThreadScheduler
import com.technicaltest.stubhub.domain.CharactersRepository
import com.technicaltest.stubhub.domain.MarvelCharacter
import com.technicaltest.stubhub.domain.MarvelCharacters
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val charactersRepository: CharactersRepository,
    private val threadScheduler: ThreadScheduler
) : ViewModel() {

    private val _characters = MutableLiveData<MarvelCharacters>()
    val characters: LiveData<MarvelCharacters> get() = _characters

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _error = SingleLiveEvent<CharactersError>()
    val error: LiveData<CharactersError> get() = _error

    private val _navigation = SingleLiveEvent<CharactersNavigation>()
    val navigation: LiveData<CharactersNavigation> get() = _navigation

    private val disposable: CompositeDisposable = CompositeDisposable()
    private var lastTextQueried = ""

    init {
        getCharacters("", 0)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    fun onCharacterClick(marvelCharacter: MarvelCharacter) {
        _navigation.value = CharactersNavigation.Details(marvelCharacter)
    }

    fun onLoadMoreCharacters(itemsCount: Int) {
        getCharacters("", itemsCount)
    }

    fun onSearchTextUpdated(name: String) {
        if (name != lastTextQueried) {
            lastTextQueried = name
            _characters.value = MarvelCharacters(0, listOf())
            getCharacters(name, 0)
        }
    }

    private fun getCharacters(name: String, itemsCount: Int) {
        _loading.value = true
        disposable.clear()
        disposable.add(
            charactersRepository.getCharacters(name, itemsCount)
                .observeOn(threadScheduler.getMainThread())
                .subscribeOn(threadScheduler.getIoThread())
                .subscribe({
                    _loading.value = false
                    handleCharacters(it)
                }, {
                    _loading.value = false
                    _error.value = CharactersError.Unknown
                })
        )
    }

    private fun handleCharacters(marvelCharacters: MarvelCharacters) {
        val characters = (_characters.value?.characters ?: mutableListOf()) + marvelCharacters.characters
        if (characters.isEmpty()) {
            _error.value = CharactersError.CharactersNotFound
        } else {
            _characters.value = MarvelCharacters(
                total = marvelCharacters.total,
                characters = characters
            )
        }
    }

}