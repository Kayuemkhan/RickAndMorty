

package com.example.rickandmorty.view.details

import androidx.databinding.Bindable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.data.repository.DetailRepository
import com.example.rickandmorty.network.model.CharacterDetailsResponse
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.asBindingProperty
import com.skydoves.bindables.bindingProperty
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import timber.log.Timber

class DetailViewModel @AssistedInject constructor(
  detailRepository: DetailRepository,
  @Assisted private val id: Int
) : BindingViewModel() {

  @get:Bindable
  var isLoading: Boolean by bindingProperty(true)
    private set

  @get:Bindable
  var toastMessage: String? by bindingProperty(null)
    private set


  private val pokemonFetchingIndex: MutableStateFlow<Int> = MutableStateFlow(0)

  private val pokemonInfoFlow= pokemonFetchingIndex.flatMapLatest {_ ->
    detailRepository.fetchPokemonInfo(
      name = id,
      onSuccess = { isLoading = false },
      onError = { toastMessage = it }
    )
  }

  @get:Bindable
  val pokemonInfo: CharacterDetailsResponse? by pokemonInfoFlow.asBindingProperty(viewModelScope, null)

  init {
    Timber.d("init DetailViewModel")
  }

  @dagger.assisted.AssistedFactory
  interface AssistedFactory {
    fun create(pokemonName: Int): DetailViewModel
  }

  companion object {
    fun provideFactory(
      assistedFactory: AssistedFactory,
      pokemonName: Int
    ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {

      @Suppress("UNCHECKED_CAST")
      override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return assistedFactory.create(pokemonName) as T
      }
    }
  }
}
