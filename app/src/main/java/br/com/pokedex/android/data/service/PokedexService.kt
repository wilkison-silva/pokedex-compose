package br.com.pokedex.android.data.service

import br.com.pokedex.android.data.service.responses.Pokemon
import br.com.pokedex.android.data.service.responses.PokemonData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokedexService {

    @GET("pokemon/{name}")
    suspend fun getPokemonDetailsByName(
        @Path("name") name: String
    ): Response<Pokemon>

    @GET("pokemon")
    suspend fun getPokemonList(): Response<PokemonData>
}