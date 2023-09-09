

package com.example.rickandmorty.view.details

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.ActivityDetailBinding
import com.example.rickandmorty.model.Results
import com.example.rickandmorty.view.details.DetailViewModel.Companion.fetchPokemonInfo
import com.google.gson.Gson
import com.skydoves.bindables.BindingActivity
import com.skydoves.transformationlayout.TransformationCompat
import com.skydoves.transformationlayout.TransformationCompat.onTransformationEndContainerApplyParams
import com.skydoves.transformationlayout.TransformationLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : BindingActivity<ActivityDetailBinding>(R.layout.activity_detail) {

  @get:VisibleForTesting
  internal val viewModel: DetailViewModel by viewModels()


  private val pokemonItem: Results = results


  override fun onCreate(savedInstanceState: Bundle?) {
    onTransformationEndContainerApplyParams(this)
    super.onCreate(savedInstanceState)


    binding {

      pokemon = pokemonItem
      vm = viewModel.apply {
        fetchPokemonInfo(pokemonItem.id!!)
      }
    }
  }

  companion object {
    @VisibleForTesting
    internal const val EXTRA_POKEMON = "EXTRA_POKEMON"
    internal var results = Results()

    fun startActivity(transformationLayout: TransformationLayout, result: Results) {

      results = result

      val context = transformationLayout.context
      if (context is Activity) {
        val gson = Gson()
        val jsonString = gson.toJson(results)


        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(EXTRA_POKEMON, jsonString)

        TransformationCompat.startActivity(transformationLayout, intent)
      }
    }
  }
}