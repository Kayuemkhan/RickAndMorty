package com.example.rickandmorty.data.api
import javax.inject.Inject

class ApiRepository @Inject constructor(
  private val apiService: ApiService,
  private val apiResponse: ApiResponse,
) {

  suspend fun fetchPokemonList(
    page: Int
  ) = apiService.fetchPokemonList(
    page
  )


}
