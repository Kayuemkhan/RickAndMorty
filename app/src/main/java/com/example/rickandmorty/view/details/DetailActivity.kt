

package com.example.rickandmorty.view.details

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.ActivityDetailBinding
import com.example.rickandmorty.model.Results
import com.skydoves.bindables.BindingActivity
import com.skydoves.transformationlayout.TransformationCompat
import com.skydoves.transformationlayout.TransformationCompat.onTransformationEndContainerApplyParams
import com.skydoves.transformationlayout.TransformationLayout
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailActivity : BindingActivity<ActivityDetailBinding>(R.layout.activity_detail) {

  @set:Inject
  internal lateinit var detailViewModelFactory: DetailViewModel.AssistedFactory

  @get:VisibleForTesting
  internal val viewModel: DetailViewModel by viewModels {
    DetailViewModel.provideFactory(detailViewModelFactory, pokemonItem.id!!)
  }

  val pokemonItem: Results = requireNotNull(intent.getParcelableExtra(EXTRA_POKEMON))

  override fun onCreate(savedInstanceState: Bundle?) {
    TransformationCompat.onTransformationEndContainerApplyParams(this)
    super.onCreate(savedInstanceState)
    binding {
      pokemon = pokemonItem
      vm = viewModel
    }
  }

  companion object {
    @VisibleForTesting
    internal const val EXTRA_POKEMON = "EXTRA_POKEMON"

    fun startActivity(transformationLayout: TransformationLayout, pokemon: Results) {
      val context = transformationLayout.context
      if (context is Activity) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(EXTRA_POKEMON, pokemon)
        TransformationCompat.startActivity(transformationLayout, intent)
      }
    }
  }
}