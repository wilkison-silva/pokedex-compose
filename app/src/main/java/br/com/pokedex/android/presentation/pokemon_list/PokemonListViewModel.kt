package br.com.pokedex.android.presentation.pokemon_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.pokedex.android.data.Resource
import br.com.pokedex.android.data.service.responses.PokemonData
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

    fun getPokemonList() {
        viewModelScope.launch {
            _uiState.value = PokemonListState.Loading
            pokemonRepository.getPokemonList().collect { resource ->
                when (resource) {
                    is Resource.Error -> _uiState.value = PokemonListState.Error
                    is Resource.Sucess -> {
                        try {
                            val pokemonsData = resource.data as PokemonData
                            _uiState.value = PokemonListState.Success(pokemonsData)
                        }
                        catch (e: java.lang.Exception) {
                            _uiState.value = PokemonListState.Error
                        }
                    }
                }
            }
        }
    }

}