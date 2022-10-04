package br.com.pokedex.android.data.repository

import br.com.pokedex.android.data.service.PokedexService
import br.com.pokedex.android.domain.repository.GetPokemonListResult
import br.com.pokedex.android.domain.repository.GetPokemonResult
import br.com.pokedex.android.domain.repository.PokemonRepository
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokedexService: PokedexService
) : PokemonRepository {

    override suspend fun getPokemonList(limit: Int, offset: Int): GetPokemonListResult {
        return try {
            val response = pokedexService.getPokemonList(limit, offset)
            GetPokemonListResult.Success(response)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            GetPokemonListResult.Error
        }

    }

    override suspend fun getPokemonDetailsByName(name: String): GetPokemonResult {
        return try {
            val response = pokedexService.getPokemonDetailsByName(name)
            GetPokemonResult.Success(response)

        } catch (e: java.lang.Exception) {
            GetPokemonResult.Error
        }
    }

}