
package com.example.rickandmorty.ui.main

import androidx.annotation.MainThread
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.example.rickandmorty.base.LiveCoroutinesViewModel
import com.example.rickandmorty.data.model.Results
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(
  private val mainRepository: MainRepository
) : LiveCoroutinesViewModel() {

  private var pokemonFetchingLiveData: MutableLiveData<Int> = MutableLiveData()
  val pokemonListLiveData: LiveData<List<Results>>

  private val _toastLiveData: MutableLiveData<String> = MutableLiveData()
  val toastLiveData: LiveData<String> get() = _toastLiveData

  val isLoading: ObservableBoolean = ObservableBoolean(false)

  init {
    Timber.d("init MainViewModel")

    pokemonListLiveData = pokemonFetchingLiveData.switchMap {
      isLoading.set(true)
      launchOnViewModelScope {
        this.mainRepository.fetchPokemonList(
          page = it,
          onSuccess = { isLoading.set(false) },
          onError = { _toastLiveData.postValue(it.toString()) }
        ).asLiveData()
      }
    }
  }

  @MainThread
  fun fetchPokemonList(page: Int) {
    pokemonFetchingLiveData.value = page
  }
}
