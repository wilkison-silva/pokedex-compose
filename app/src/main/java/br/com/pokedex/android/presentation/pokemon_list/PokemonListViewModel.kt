package br.com.pokedex.android.presentation.pokemon_list

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import br.com.pokedex.android.domain.model.PAGE_SIZE
import br.com.pokedex.android.domain.repository.GetPokemonListResult
import br.com.pokedex.android.domain.repository.PokemonRepository
import br.com.pokedex.android.presentation.model.PokemonItemView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    private val _pokemonListState: MutableStateFlow<PokemonListState> =
        MutableStateFlow(PokemonListState.EmptyState)
    val pokemonListState = _pokemonListState.asStateFlow()

    private val _endPageReached = MutableStateFlow(false)
    val endPageReached = _endPageReached.asStateFlow()

    private var currentPage = 0

    private var pokemonItemViewList = listOf<PokemonItemView>()

    private val _isSearchingState = MutableStateFlow(false)
    val isSearchingState = _isSearchingState.asStateFlow()

    init {
        getPokemonList()
    }

    fun calculateDominantColor(
        drawable: Drawable,
        onFinish: (color: Color) -> Unit
    ) {
        val bitmap = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)
        Palette.from(bitmap).generate { palette ->
            palette?.dominantSwatch?.rgb?.let { colorValue: Int ->
                onFinish(Color(colorValue))
            }
        }
    }

    fun getPokemonList() {
        viewModelScope.launch {
            when (val pokemonListResult =
                pokemonRepository.getPokemonList(PAGE_SIZE, currentPage * PAGE_SIZE)) {
                is GetPokemonListResult.Error -> {
                    _pokemonListState.value = PokemonListState.Error
                }
                is GetPokemonListResult.Success -> {
                    _endPageReached.value =
                        currentPage * PAGE_SIZE >= pokemonListResult.pokemonList.count
                    val currentPokemonList =
                        pokemonListResult.pokemonList.results.map { result ->
                            val number = if (result.url.endsWith("/")) {
                                result.url.dropLast(1).takeLastWhile { it.isDigit() }
                            } else {
                                result.url.takeLastWhile { it.isDigit() }
                            }
                            val url =
                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${number}.png"
                            PokemonItemView(
                                pokemonName = result.name.replaceFirstChar {
                                    if (it.isLowerCase()) it.titlecase(Locale.ROOT)
                                    else it.toString()
                                },
                                imageURL = url,
                                number = number.toInt()
                            )
                        }
                    currentPage += 1
                    pokemonItemViewList += currentPokemonList

                    _pokemonListState.value = PokemonListState.Success(pokemonItemViewList)
                }
            }
        }
    }

    fun searchByName(name: String) {
        if (name.isNotBlank()) {
            val result = pokemonItemViewList.filter {
                it.pokemonName.startsWith(prefix = name, ignoreCase = true)
            }
            _pokemonListState.value = PokemonListState.Success(result)
            _isSearchingState.value = true
        } else {
            _pokemonListState.value = PokemonListState.Success(pokemonItemViewList)
            _isSearchingState.value = false
        }
    }

}