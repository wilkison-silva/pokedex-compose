package br.com.pokedex.android.presentation.pokemon_list.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.pokedex.android.presentation.pokemon_list.PokemonListState
import br.com.pokedex.android.presentation.pokemon_list.PokemonListViewModel

@Composable
fun PokemonListScreen(
    viewModel: PokemonListViewModel = hiltViewModel()
) {
    when (val uiState = viewModel.uiState.collectAsState().value) {
        is PokemonListState.EmptyState -> {

        }
        is PokemonListState.Error -> {
            Text(text = "Error")
        }
        is PokemonListState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier,
                )
            }
        }
        is PokemonListState.Success -> {
            val pokemonList = uiState.pokemonList
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(pokemonList.results.size) {
                    Text(text = pokemonList.results[it].name)
                }
            }
        }
    }

}