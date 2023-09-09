

package com.example.rickandmorty.view.details

import androidx.annotation.MainThread
import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.data.repository.DetailRepository
import com.example.rickandmorty.network.model.CharacterDetailsResponse
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.asBindingProperty
import com.skydoves.bindables.bindingProperty
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(
  detailRepository: DetailRepository,
) : BindingViewModel()
{

  private var id: MutableLiveData<Int> = MutableLiveData()
  private val defaultValue =1
  @get:Bindable
  var isLoading: Boolean by bindingProperty(true)
    private set

  @get:Bindable
  var toastMessage: String? by bindingProperty(null)
    private set


  private val pokemonFetchingIndex: MutableStateFlow<Int> = MutableStateFlow(0)

  private val pokemonInfoFlow= pokemonFetchingIndex.flatMapLatest { _ ->

    id.value = idFromPassed
    detailRepository.fetchPokemonInfo(
      name = id.value ?: defaultValue,
      onSuccess = { isLoading = false },
      onError = { toastMessage = it }
    )
  }

  @get:Bindable
  val pokemonInfo: CharacterDetailsResponse? by pokemonInfoFlow.asBindingProperty(viewModelScope, null)

  init {
    Timber.d("init DetailViewModel")

  }

  companion object{

    internal var idFromPassed =1
    @MainThread
    fun fetchPokemonInfo(name: Int) {
      idFromPassed = name
    }
  }
}
