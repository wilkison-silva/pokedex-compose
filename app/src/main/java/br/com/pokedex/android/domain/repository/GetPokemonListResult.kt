package br.com.pokedex.android.domain.repository

import br.com.pokedex.android.data.service.responses.PokemonList

sealed class GetPokemonListResult {
    data class Success(val pokemonList: PokemonList) : GetPokemonListResult()
    object Error : GetPokemonListResult()
}