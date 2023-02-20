package com.technicaltest.stubhub.login.presentation.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.technicaltest.stubhub.core.presentation.ThreadScheduler
import com.technicaltest.stubhub.login.presentation.domain.CharactersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val charactersRepository: CharactersRepository,
    private val threadScheduler: ThreadScheduler
) : ViewModel() {

    private val _state = MutableLiveData<CharacterState>()
    val state: LiveData<CharacterState> get() = _state

    private val disposable: CompositeDisposable = CompositeDisposable()

    init {
        _state.value = CharacterState(loading = true)
        getCharacters("")
    }

    private fun getCharacters(name: String) {
        disposable.clear()
        disposable.add(
            charactersRepository.getCharacters(name)
                .observeOn(threadScheduler.getMainThread())
                .subscribeOn(threadScheduler.getIoThread())
                .subscribe({
                    _state.value = _state.value?.copy(characters = it, loading = false)
                }, {
                    val a = it
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    fun onCharacterClick(id: String) {

    }
}