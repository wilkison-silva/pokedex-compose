package br.com.pokedex.android.presentation.pokemon_details.states

import br.com.pokedex.android.data.service.responses.Pokemon

sealed class PokemonDetailsState {
    data class Success(val pokemon: Pokemon) : PokemonDetailsState()
    object Error : PokemonDetailsState()
    object Loading : PokemonDetailsState()
}