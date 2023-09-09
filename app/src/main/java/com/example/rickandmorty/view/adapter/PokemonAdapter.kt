
package com.example.rickandmorty.view.adapter

import android.os.SystemClock
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import com.example.rickandmorty.model.Results
import com.example.rickandmorty.view.adapter.PokemonAdapter.PokemonViewHolder
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.ItemPokemonBinding
import com.example.rickandmorty.view.details.DetailActivity
import com.google.gson.Gson

class PokemonAdapter : RecyclerView.Adapter<PokemonViewHolder>() {

  private val items: MutableList<Results> = mutableListOf()
  private var onClickedAt = 0L

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    val binding =
      DataBindingUtil.inflate<ItemPokemonBinding>(inflater, R.layout.item_pokemon, parent, false)
    return PokemonViewHolder(binding).apply {
      binding.root.setOnClickListener {
        adapterPosition.takeIf { it != NO_POSITION }
          ?: return@setOnClickListener
        val currentClickedAt = SystemClock.elapsedRealtime()
        if (currentClickedAt - onClickedAt > binding.transformationLayout.duration) {

          println("items")
          println(Gson().toJson(items[position]))
          DetailActivity.startActivity(binding.transformationLayout, items[position])
          onClickedAt = currentClickedAt
        }
      }
    }
  }

  fun addPokemonList(pokemonList: List<Results>) {
    val previous = items.size
    items.addAll(pokemonList)
    notifyItemRangeChanged(0, pokemonList.size)
  }

  override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
    holder.binding.apply {
      pokemon = items[position]
      executePendingBindings()
    }
  }

  override fun getItemCount() = items.size

  class PokemonViewHolder(val binding: ItemPokemonBinding) :
    RecyclerView.ViewHolder(binding.root)
}
