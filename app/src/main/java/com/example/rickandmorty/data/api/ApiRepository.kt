package com.example.rickandmorty.data.api

import com.gplex.wallboard.data.api.ApiResponse

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ApiRepository @Inject constructor(
  private val apiService: ApiService,
  private val apiResponse: ApiResponse,
) {




}
