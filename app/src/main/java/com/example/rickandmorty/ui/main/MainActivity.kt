
package com.example.rickandmorty.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import com.example.rickandmorty.base.DataBindingActivity
import com.example.rickandmorty.ui.adapter.PokemonAdapter
import com.example.rickandmortycharacterviewer_androidprojecttask.R
import com.example.rickandmortycharacterviewer_androidprojecttask.databinding.ActivityMainBinding
import com.skydoves.transformationlayout.onTransformationStartContainer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : DataBindingActivity() {

  @VisibleForTesting val viewModel: MainViewModel by viewModels()
  private val binding: ActivityMainBinding by binding(R.layout.activity_main)

  override fun onCreate(savedInstanceState: Bundle?) {
    onTransformationStartContainer()
    super.onCreate(savedInstanceState)
    binding.apply {
      lifecycleOwner = this@MainActivity
      adapter = PokemonAdapter()
      vm = viewModel.apply { fetchPokemonList(0) }
    }
  }
  override fun onBackPressed() {

  }

}
