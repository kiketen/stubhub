package com.technicaltest.details.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.technicaltest.details.domain.CharacterApparition
import com.technicaltest.details.domain.CharacterDetailsRepository
import com.technicaltest.stubhub.core.presentation.ThreadScheduler
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val characterDetailsRepository: CharacterDetailsRepository,
    private val threadScheduler: ThreadScheduler
) : ViewModel() {

    private val id = savedStateHandle.get<String>("id")!!
    private val name = savedStateHandle.get<String>("name")!!
    private val image = savedStateHandle.get<String>("image")!!

    private val _characterDetails = MutableLiveData<CharacterDetails>()
    val characterDetails: LiveData<CharacterDetails> get() = _characterDetails

    private val _loadingComics = MutableLiveData<Boolean>()
    val loadingComics: LiveData<Boolean> get() = _loadingComics

    private val _loadingSeries = MutableLiveData<Boolean>()
    val loadingSeries: LiveData<Boolean> get() = _loadingSeries

    private val _loadingEvents = MutableLiveData<Boolean>()
    val loadingEvents: LiveData<Boolean> get() = _loadingEvents

    private val _comics = MutableLiveData<List<CharacterApparition>>()
    val comics: LiveData<List<CharacterApparition>> get() = _comics

    private val _series = MutableLiveData<List<CharacterApparition>>()
    val series: LiveData<List<CharacterApparition>> get() = _series

    private val _events = MutableLiveData<List<CharacterApparition>>()
    val events: LiveData<List<CharacterApparition>> get() = _events

    private val disposable: CompositeDisposable = CompositeDisposable()

    init {
        _characterDetails.value = CharacterDetails(name, image)
        getComics()
        getSeries()
        getEvents()
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    private fun getComics() {
        _loadingComics.value = true
        disposable.add(
            characterDetailsRepository.getComics(id)
                .observeOn(threadScheduler.getMainThread())
                .subscribeOn(threadScheduler.getIoThread())
                .subscribe({
                    _loadingComics.value = false
                    _comics.value = it
                }, {
                    _loadingComics.value = false
                })
        )
    }

    private fun getSeries() {
        _loadingSeries.value = true
        disposable.add(
            characterDetailsRepository.getSeries(id)
                .observeOn(threadScheduler.getMainThread())
                .subscribeOn(threadScheduler.getIoThread())
                .subscribe({
                    _loadingSeries.value = false
                    _series.value = it
                }, {
                    _loadingSeries.value = false
                })
        )
    }

    private fun getEvents() {
        _loadingEvents.value = true
        disposable.add(
            characterDetailsRepository.getEvents(id)
                .observeOn(threadScheduler.getMainThread())
                .subscribeOn(threadScheduler.getIoThread())
                .subscribe({
                    _loadingEvents.value = false
                    _events.value = it
                }, {
                    _loadingEvents.value = false
                })
        )
    }
}