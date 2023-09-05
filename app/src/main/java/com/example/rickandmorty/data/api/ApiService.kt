package com.example.rickandmorty.data.api


import com.example.rickandmorty.data.model.CharacterResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {
  @GET("character")
  suspend fun fetchPokemonList(
    @Query("page") page: Int = 1,
  ): ApiResponse<CharacterResponse>

}
