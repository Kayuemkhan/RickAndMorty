
package com.example.rickandmorty.view.main

import androidx.annotation.MainThread
import androidx.databinding.Bindable
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.data.model.Results
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.asBindingProperty
import com.skydoves.bindables.bindingProperty
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
  private val mainRepository: MainRepository
) : BindingViewModel() {

  @get:Bindable
  var isLoading: Boolean by bindingProperty(false)
    private set

  @get:Bindable
  var toastMessage: String? by bindingProperty(null)
    private set

  private val pokemonFetchingIndex: MutableStateFlow<Int> = MutableStateFlow(0)
  private val pokemonListFlow = pokemonFetchingIndex.flatMapLatest { page ->
    mainRepository.fetchPokemonList(
      page = page,
      onSuccess = { isLoading = true },
      onError = { toastMessage = it }
    )
  }

  @get:Bindable
  val pokemonList: List<Results> by pokemonListFlow.asBindingProperty(viewModelScope, emptyList())

  init {
    Timber.d("init MainViewModel")
  }

  @MainThread
  fun fetchNextPokemonList() {
    if (!isLoading) {
      pokemonFetchingIndex.value++
    }
  }
}
