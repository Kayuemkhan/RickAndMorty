
package com.example.rickandmorty.network

import com.example.rickandmorty.MainCoroutinesRule
import com.example.rickandmorty.network.service.ApiRepository
import com.example.rickandmorty.network.service.ApiService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import retrofit2.Response
import java.io.IOException

@ExperimentalCoroutinesApi
class RickAndMortyServiceTest : ApiAbstract<ApiService>() {

  private lateinit var service: ApiService
  private val client: ApiRepository = mock()

  @ExperimentalCoroutinesApi
  @get:Rule
  var coroutinesRule = MainCoroutinesRule()

  @Before
  fun initService() {
    service = createService(ApiService::class.java)
  }

  @Throws(IOException::class)
  @Test
  fun fetchPokemonListFromNetworkTest() = runBlocking {
    enqueueResponse("/PokemonResponse.json")
    val response = service.fetchPokemonList()
    val responseBody = requireNotNull((response as Response.T).data)
    mockWebServer.takeRequest()

    client.fetchPokemonList(page = 0)
    assertThat(responseBody.count, `is`(964))
    assertThat(responseBody.results[0].name, `is`("bulbasaur"))
    assertThat(responseBody.results[0].url, `is`("https://rickandmortyapi.com/"))
  }

  @Throws(IOException::class)
  @Test
  fun fetchPokemonInfoFromNetworkTest() = runBlocking {
    enqueueResponse("/Bulbasaur.json")
    val response = service.fetchPokemonInfo("bulbasaur")
    val responseBody = requireNotNull((response as Response.T).data)
    mockWebServer.takeRequest()

    client.fetchPokemonInfo(name = "bulbasaur")
    assertThat(responseBody.id, `is`(1))
    assertThat(responseBody.name, `is`("bulbasaur"))
    assertThat(responseBody.height, `is`(7))
    assertThat(responseBody.weight, `is`(69))
    assertThat(responseBody.experience, `is`(64))
  }
}
