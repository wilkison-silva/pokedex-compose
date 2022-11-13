package br.com.pokedex.android.presentation.pokemon_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import br.com.pokedex.android.R
import br.com.pokedex.android.presentation.pokemon_list.PokemonListState
import br.com.pokedex.android.presentation.pokemon_list.PokemonListViewModel


@Composable
fun PokemonListScreen(
    viewModel: PokemonListViewModel = hiltViewModel(),
    navController: NavController
) {
    val endReached = viewModel.endPageReached.collectAsState().value
    val uiState = viewModel.pokemonListState.collectAsState().value
    val isSearching = viewModel.isSearchingState.collectAsState().value

    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Spacer(modifier = Modifier.height(20.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_international_pok_mon_logo),
                contentDescription = "Pokemon logo",
                modifier = Modifier
                    .fillMaxWidth()
                    .align(CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(16.dp))
            SearchBar(
                hint = "Search",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                viewModel.searchByName(it)
            }
            Spacer(modifier = Modifier.height(16.dp))
            when (uiState) {
                is PokemonListState.EmptyState -> {

                }
                is PokemonListState.Error -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Error",
                            modifier = Modifier,
                        )
                    }
                }
                is PokemonListState.Success -> {
                    LazyColumn(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                    ) {
                        val itemCount = if (uiState.pokemonItemViewList.size % 2 == 0) {
                            uiState.pokemonItemViewList.size / 2
                        } else {
                            uiState.pokemonItemViewList.size / 2 + 1
                        }
                        items(itemCount) {
                            if (it >= itemCount - 1 && !endReached && !isSearching) {
                                viewModel.getPokemonList()
                            }
                            PokedexRow(
                                rowIndex = it,
                                pokemonItemViewsList = uiState.pokemonItemViewList,
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}