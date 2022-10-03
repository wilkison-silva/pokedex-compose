package br.com.pokedex.android.presentation.pokemon_list

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PokemonListActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokemonList()
        }
    }
}

@Composable
fun PokemonList(
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
            val pokemonsData = uiState.pokemonList
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(pokemonsData.results.size) {
                    Text(text = pokemonsData.results[it].name)
                }
            }
        }
    }

}