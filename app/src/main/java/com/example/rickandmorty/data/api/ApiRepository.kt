package com.example.rickandmorty.data.api
import com.example.rickandmorty.data.model.CharacterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class ApiRepository @Inject constructor(
  private val apiService: ApiService,
  private val apiResponse: ApiResponse,
) {

  suspend fun fetchPokemonList(
    page: Int
  ): Response<CharacterResponse>
  =
        apiService.fetchPokemonList(page)



}
