
package com.example.rickandmorty.binding

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
import com.example.rickandmorty.data.model.Results
import com.example.rickandmorty.view.adapter.PokemonAdapter
import com.example.rickandmorty.view.main.MainViewModel
import com.google.gson.Gson
import com.skydoves.bindables.BindingListAdapter

//@BindingAdapter("adapter")
//fun bindAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
//  view.adapter = adapter
//}
//
//
//
//@BindingAdapter("adapterPokemonList")
//fun bindAdapterPokemonList(view: RecyclerView, pokemonList: List<Results>?) {
//  pokemonList.let {
//    (view.adapter as? PokemonAdapter)?.addPokemonList(it!!)
//  }
//}
object RecyclerViewBinding {

  @JvmStatic
  @BindingAdapter("adapter")
  fun bindAdapter(view: RecyclerView, adapter: Adapter<*>) {
    view.adapter = adapter.apply {
      stateRestorationPolicy = PREVENT_WHEN_EMPTY
    }
  }





  @JvmStatic
  @BindingAdapter("adapterPokemonList")
  fun bindAdapterPokemonList(view: RecyclerView, pokemonList: List<Results>?) {
    pokemonList.let {
      if (it != null) {
        println("ITFound")
        println(Gson().toJson(it))
        (view.adapter as? PokemonAdapter)?.addPokemonList(it)
      }
    }
  }


  @JvmStatic
  @BindingAdapter("loading")
  fun setGone(view: View, isGone: Boolean) {
    view.visibility = if (isGone) View.GONE else View.VISIBLE
  }
}
