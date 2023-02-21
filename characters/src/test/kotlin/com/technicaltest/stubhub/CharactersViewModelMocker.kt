package com.technicaltest.stubhub

import com.technicaltest.stubhub.core.presentation.ThreadScheduler
import com.technicaltest.stubhub.domain.CharactersRepository
import com.technicaltest.stubhub.domain.MarvelCharacters
import com.technicaltest.stubhub.presentation.CharactersViewModel
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class CharactersViewModelMocker {

    private val charactersRepository: CharactersRepository = mockk()
    private val threadScheduler: ThreadScheduler = mockk()

    fun init(): CharactersViewModel {
        defaultMocks()
        return CharactersViewModel(
            charactersRepository = charactersRepository,
            threadScheduler = threadScheduler
        )
    }

    private fun defaultMocks() {
        every { threadScheduler.getIoThread() } returns Schedulers.trampoline()
        every { threadScheduler.getMainThread() } returns Schedulers.trampoline()
    }

    fun publishCharacters(): PublishSubject<MarvelCharacters> {
        val stream = PublishSubject.create<MarvelCharacters>()
        mockCharacters { stream }
        return stream
    }

    private fun mockCharacters(result: () -> Observable<MarvelCharacters>) {
        every { charactersRepository.getCharacters(any(), any()) } returns result()
    }

}