package com.technicaltest.stubhub.login.presentation.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.technicaltest.stubhub.core.presentation.ThreadScheduler
import com.technicaltest.stubhub.login.presentation.domain.CharactersRepository
import com.technicaltest.stubhub.login.presentation.domain.MarvelCharacters
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

    private val disposable: CompositeDisposable = CompositeDisposable()

    init {
        getCharacters("", 0)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    fun onCharacterClick(id: String) {

    }

    fun onLoadMoreCharacters(itemsCount: Int) {
        getCharacters("", itemsCount)
    }

    fun onSearchTextUpdated(name: String) {
        _characters.value = MarvelCharacters(0, listOf())
        getCharacters(name, 0)
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
                    val characters = _characters.value?.characters ?: mutableListOf()
                    _characters.value = MarvelCharacters(
                        total = it.total,
                        characters = characters + it.characters
                    )
                }, {
                    _loading.value = false
                })
        )
    }

}