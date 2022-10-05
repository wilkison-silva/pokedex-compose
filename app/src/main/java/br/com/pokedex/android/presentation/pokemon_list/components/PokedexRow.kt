package br.com.pokedex.android.presentation.pokemon_list.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.pokedex.android.presentation.model.PokemonItemView

@Composable
fun PokedexRow(
    rowIndex: Int,
    pokemonItemViewsList: List<PokemonItemView>,
    navController: NavController
) {
    Column(

    ) {
        Row {
            PokemonCard(
                pokemonItemView = pokemonItemViewsList[rowIndex * 2],
                navController = navController,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(16.dp))
            if (pokemonItemViewsList.size >= ((rowIndex * 2) + 2)) {
                PokemonCard(
                    pokemonItemView = pokemonItemViewsList[rowIndex * 2 + 1],
                    navController = navController,
                    modifier = Modifier.weight(1f)
                )
            } else {
                Spacer(modifier = Modifier.weight(1f))
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}