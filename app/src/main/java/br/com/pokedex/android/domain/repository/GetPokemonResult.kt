package br.com.pokedex.android.domain.repository

import br.com.pokedex.android.data.service.responses.Pokemon

sealed class GetPokemonResult {
    data class Success(val pokemon: Pokemon) : GetPokemonResult()
    object Error : GetPokemonResult()
}