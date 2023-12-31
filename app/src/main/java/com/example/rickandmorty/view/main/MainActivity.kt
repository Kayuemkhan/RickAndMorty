
package com.example.rickandmorty.view.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.Observer
import com.example.rickandmorty.base.DataBindingActivity
import com.example.rickandmorty.view.adapter.PokemonAdapter
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.ActivityMainBinding
import com.skydoves.bindables.BindingActivity
import com.skydoves.transformationlayout.onTransformationStartContainer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

  @get:VisibleForTesting
  internal val viewModel: MainViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    onTransformationStartContainer()
    super.onCreate(savedInstanceState)
    binding {
      adapter = PokemonAdapter()
      vm = viewModel
    }
  }
}