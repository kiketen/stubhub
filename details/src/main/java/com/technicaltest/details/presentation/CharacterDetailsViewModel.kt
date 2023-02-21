package com.technicaltest.details.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.technicaltest.stubhub.core.presentation.ThreadScheduler
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val threadScheduler: ThreadScheduler
) : ViewModel() {

    private val id = savedStateHandle.get<String>("id")!!
    private val name = savedStateHandle.get<String>("name")!!
    private val image = savedStateHandle.get<String>("image")!!

    private val _characterDetails = MutableLiveData<CharacterDetails>()
    val characterDetails: LiveData<CharacterDetails> get() = _characterDetails

    private val disposable: CompositeDisposable = CompositeDisposable()

    init {
        _characterDetails.value = CharacterDetails(name, image)

    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    fun test() {

    }


}