package com.technicaltest.stubhub

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.technicaltest.stubhub.domain.MarvelCharacter
import com.technicaltest.stubhub.domain.MarvelCharacters
import com.technicaltest.stubhub.presentation.CharactersViewModel
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CharactersViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var mocker: CharactersViewModelMocker
    private lateinit var viewModel: CharactersViewModel
    private lateinit var asserter: CharactersViewModelAsserter

    private val mockedMarvelCharacter = mockk<MarvelCharacter>()
    private val mockedMarvelCharacters = mockk<MarvelCharacters>()

    @Before
    fun setup() {
        mocker = CharactersViewModelMocker()
    }

    @Test
    fun givenCharactersWhenInitThenShowCharacters() {
        initMockedObjects(listOf(mockedMarvelCharacter))
        val stream = mocker.publishCharacters()
        initViewModelAndAsserter()
        asserter.loadingVisibility(true)
        stream.onNext(mockedMarvelCharacters)
        asserter.charactersLoaded(mockedMarvelCharacters)
    }

    @Test
    fun givenEmptyCharactersWhenInitThenShowErrorNotFound() {
        initMockedObjects(listOf())
        val stream = mocker.publishCharacters()
        initViewModelAndAsserter()
        asserter.loadingVisibility(true)
        stream.onNext(mockedMarvelCharacters)
        asserter.charactersEmptyError()
    }

    @Test
    fun givenUnknownErrorWhenInitThenShowUnknownError() {
        val stream = mocker.publishCharacters()
        initViewModelAndAsserter()
        asserter.loadingVisibility(true)
        stream.onError(Throwable())
        asserter.unknownError()
    }

    private fun initMockedObjects(characters: List<MarvelCharacter>) {
        every { mockedMarvelCharacters.characters } returns characters
        every { mockedMarvelCharacters.total } returns 100
    }

    private fun initViewModelAndAsserter() {
        viewModel = mocker.init()
        asserter = CharactersViewModelAsserter(viewModel)
    }

}