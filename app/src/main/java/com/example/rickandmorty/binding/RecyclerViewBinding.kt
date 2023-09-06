
package com.example.rickandmorty.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
import com.example.rickandmorty.view.main.MainViewModel
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

//  @JvmStatic
//  @BindingAdapter("submitList")
//  fun bindSubmitList(view: RecyclerView, itemList: List<Any>?) {
//    view.adapter.whatIfNotNullAs<BindingListAdapter<Any, *>> { adapter ->
//      adapter.submitList(itemList)
//    }
//  }

//  @JvmStatic
//  @BindingAdapter("paginationPokemonList")
//  fun paginationPokemonList(view: RecyclerView, viewModel: MainViewModel) {
//    RecyclerViewPaginator(
//      recyclerView = view,
//      isLoading = { viewModel.isLoading },
//      loadMore = { viewModel.fetchNextPokemonList() },
//      onLast = { false }
//    ).run {
//      threshold = 8
//    }
//  }
}
