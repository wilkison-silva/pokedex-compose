package br.com.pokedex.android.presentation.pokemon_details

import androidx.lifecycle.ViewModel
import br.com.pokedex.android.domain.repository.GetPokemonResult
import br.com.pokedex.android.domain.repository.PokemonRepository
import br.com.pokedex.android.presentation.pokemon_details.states.PokemonDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    private val _pokemonState: MutableStateFlow<PokemonDetailsState> =
        MutableStateFlow(PokemonDetailsState.Loading)
    val pokemonDetailsState = _pokemonState.asStateFlow()

    suspend fun getPokemonDetails(pokemonName: String) {

            when (val result = repository.getPokemonDetailsByName(name = pokemonName)) {
                is GetPokemonResult.Error -> {
                    _pokemonState.value = PokemonDetailsState.Error
                }
                is GetPokemonResult.Success -> {
                    _pokemonState.value = PokemonDetailsState.Success(pokemon = result.pokemon)
                }
            }

    }

}