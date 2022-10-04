package br.com.pokedex.android.data.service

import br.com.pokedex.android.data.service.responses.Pokemon
import br.com.pokedex.android.data.service.responses.PokemonList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokedexService {

    @GET("pokemon/{name}")
    suspend fun getPokemonDetailsByName(
        @Path("name") name: String
    ): Pokemon

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): PokemonList
}