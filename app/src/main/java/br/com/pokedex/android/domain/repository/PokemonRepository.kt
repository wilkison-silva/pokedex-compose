package br.com.pokedex.android.domain.repository

import br.com.pokedex.android.data.Resource
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    fun getPokemonList(): Flow<Resource>

    fun getPokemonDetailsByName(name: String): Flow<Resource>

}