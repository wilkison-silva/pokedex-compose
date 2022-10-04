package br.com.pokedex.android.presentation.pokemon_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.pokedex.android.domain.repository.GetPokemonListResult
import br.com.pokedex.android.domain.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<PokemonListState> =
        MutableStateFlow(PokemonListState.EmptyState)
    val uiState = _uiState.asStateFlow()

    init {
        getPokemonList()
    }

    private fun getPokemonList(limit: Int = 100, offset: Int = 20) {
        viewModelScope.launch {
            _uiState.value = PokemonListState.Loading
            when (val pokemonListResult = pokemonRepository.getPokemonList(limit, offset)) {
                is GetPokemonListResult.Error -> {
                    _uiState.value = PokemonListState.Error
                }
                is GetPokemonListResult.Success -> {
                    _uiState.value = PokemonListState.Success(pokemonListResult.pokemonList)
                }
            }
        }
    }
}