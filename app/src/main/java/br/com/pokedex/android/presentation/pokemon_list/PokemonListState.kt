package br.com.pokedex.android.presentation.pokemon_list

import br.com.pokedex.android.presentation.model.PokemonItemView

sealed class PokemonListState {
    data class Success(val pokemonItemViewList: List<PokemonItemView>) : PokemonListState()
    object Error : PokemonListState()
    object EmptyState : PokemonListState()
}