
package com.example.rickandmorty.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.data.model.Results
import com.example.rickandmorty.ui.adapter.PokemonAdapter
import com.example.rickandmorty.ui.main.MainViewModel

@BindingAdapter("adapter")
fun bindAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
  view.adapter = adapter
}



@BindingAdapter("adapterPokemonList")
fun bindAdapterPokemonList(view: RecyclerView, pokemonList: List<Results>?) {
  pokemonList.let {
    (view.adapter as? PokemonAdapter)?.addPokemonList(it!!)
  }
}
