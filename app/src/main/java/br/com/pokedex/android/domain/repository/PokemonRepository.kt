package br.com.pokedex.android.domain.repository

interface PokemonRepository {

    suspend fun getPokemonList(limit: Int, offset: Int): GetPokemonListResult

    suspend fun getPokemonDetailsByName(name: String): GetPokemonResult

}