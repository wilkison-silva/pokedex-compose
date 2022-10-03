package br.com.pokedex.android.presentation.pokemon_list

import br.com.pokedex.android.data.service.responses.PokemonData

sealed class PokemonListState {
    data class Success(val pokemonList: PokemonData) : PokemonListState()
    object Error : PokemonListState()
    object Loading : PokemonListState()
    object EmptyState : PokemonListState()
}