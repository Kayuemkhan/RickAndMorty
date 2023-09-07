
package com.example.rickandmorty.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.asLiveData
import com.example.rickandmorty.MainCoroutinesRule
import com.example.rickandmorty.data.repository.MainRepository
import com.example.rickandmorty.database.RickMortyDao
import com.example.rickandmorty.model.Results
import com.example.rickandmorty.network.service.ApiRepository
import com.example.rickandmorty.network.service.ApiService
import com.example.rickandmorty.view.main.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.internal.util.MockUtil

@ExperimentalCoroutinesApi
class MainViewModelTest {

  private lateinit var viewModel: MainViewModel
  private lateinit var mainRepository: MainRepository
  private val pokedexService: ApiService = mock()
  private val pokdexClient: ApiRepository = ApiRepository(pokedexService)
  private val pokemonDao: RickMortyDao = mock()

  @ExperimentalCoroutinesApi
  @get:Rule
  var coroutinesRule = MainCoroutinesRule()

  @get:Rule
  var instantExecutorRule = InstantTaskExecutorRule()

  @ExperimentalCoroutinesApi
  @Before
  fun setup() {
    mainRepository = MainRepository(pokdexClient, pokemonDao)
    viewModel = MainViewModel(mainRepository)
  }

  @Test
  fun fetchPokemonListTest() = runBlocking {
    val mockData = MockUtil.mockPokemonList()

    val observer: Observer<List<Results>> = mock()
    val fetchedData: LiveData<List<Results>> =
      mainRepository.fetchPokemonList(
        onSuccess = {},
        onError = {}
      ).asLiveData()
    fetchedData.observeForever(observer)

    viewModel.fet(page = 0)
    delay(500L)

    verify(pokemonDao, atLeastOnce()).getPokemonList()
    verify(observer).onChanged(mockData)
    fetchedData.removeObserver(observer)
  }
}
