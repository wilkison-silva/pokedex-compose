package br.com.pokedex.android.data.repository

import br.com.pokedex.android.data.Resource
import br.com.pokedex.android.data.service.PokedexService
import br.com.pokedex.android.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokedexService: PokedexService
) : PokemonRepository {

    override fun getPokemonList(): Flow<Resource> {
         return flow {
             try {
                 val response = pokedexService.getPokemonList()
                 if (response.isSuccessful) {
                     response.body()?.let { pokemonList ->
                         emit(Resource.Sucess(pokemonList))
                     } ?: emit(Resource.Error)
                 }
             }
             catch (e: java.lang.Exception) {
                 e.printStackTrace()
                 emit(Resource.Error)
             }
         }
    }

    override fun getPokemonDetailsByName(name: String): Flow<Resource> {
        return flow {
            try {
                val response = pokedexService.getPokemonDetailsByName(name)
                if (response.isSuccessful) {
                    response.body()?.let { pokemon ->
                        emit(Resource.Sucess(pokemon))
                    } ?: emit(Resource.Error)
                }
            }
            catch (e: java.lang.Exception) {
                emit(Resource.Error)
            }
        }
    }

}