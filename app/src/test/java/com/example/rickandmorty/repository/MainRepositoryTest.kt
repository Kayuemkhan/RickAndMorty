
@file:Suppress("SpellCheckingInspection")

package com.example.rickandmorty.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.rickandmorty.MainCoroutinesRule
import com.example.rickandmorty.data.repository.MainRepository
import com.example.rickandmorty.database.RickMortyDao
import com.example.rickandmorty.network.service.ApiRepository
import com.example.rickandmorty.network.service.ApiService
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import retrofit2.Response

@ExperimentalCoroutinesApi
class MainRepositoryTest {

  private lateinit var repository: MainRepository
  private lateinit var client: ApiRepository
  private val service: ApiService = mock()
  private val pokemonDao: RickMortyDao = mock()

  @ExperimentalCoroutinesApi
  @get:Rule
  var coroutinesRule = MainCoroutinesRule()

  @get:Rule
  var instantExecutorRule = InstantTaskExecutorRule()

  @ExperimentalCoroutinesApi
  @Before
  fun setup() {
    client = ApiRepository(service)
    repository = MainRepository(client, pokemonDao)
  }

  @Test
  fun fetchPokemonListFromNetworkTest() = runBlocking {
    val mockData = PokemonResponse(count = 984, next = null, previous = null, results = mockPokemonList())
    whenever(pokemonDao.getPokemonList(page_ = 0)).thenReturn(emptyList())
    whenever(service.fetchPokemonList()).thenReturn(ApiResponse.of { Response.success(mockData) })

    repository.fetchPokemonList(
      page = 0,
      onSuccess = {},
      onError = {}
    ).test {
      assertEquals(expectItem()[0].page, 0)
      assertEquals(expectItem()[0].name, "bulbasaur")
      assertEquals(expectItem(), mockPokemonList())
      expectComplete()
    }

    verify(pokemonDao, atLeastOnce()).getPokemonList(page_ = 0)
    verify(service, atLeastOnce()).fetchPokemonList()
    verify(pokemonDao, atLeastOnce()).insertPokemonList(mockData.results)
  }

  @Test
  fun fetchPokemonListFromDatabaseTest() = runBlocking {
    val mockData = PokemonResponse(count = 984, next = null, previous = null, results = mockPokemonList())
    whenever(pokemonDao.getPokemonList(page_ = 0)).thenReturn(mockData.results)
    whenever(service.fetchPokemonList()).thenReturn(ApiResponse.of { Response.success(mockData) })

    repository.fetchPokemonList(
      page = 0,
      onSuccess = {},
      onError = {}
    ).test {
      assertEquals(expectItem()[0].page, 0)
      assertEquals(expectItem()[0].name, "bulbasaur")
      assertEquals(expectItem(), mockPokemonList())
      expectComplete()
    }

    verify(pokemonDao, atLeastOnce()).getPokemonList(page_ = 0)
    verifyNoMoreInteractions(service)
  }
}
