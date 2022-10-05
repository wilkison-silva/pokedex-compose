package br.com.pokedex.android.presentation.pokemon_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import br.com.pokedex.android.presentation.model.PokemonItemView
import br.com.pokedex.android.presentation.pokemon_list.PokemonListViewModel
import br.com.pokedex.android.presentation.ui.theme.RobotoCondensed
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest

@Composable
fun PokemonCard(
    modifier: Modifier = Modifier,
    pokemonItemView: PokemonItemView,
    navController: NavController,
    viewModel: PokemonListViewModel = hiltViewModel()
) {
    val defaultDominantColor = MaterialTheme.colors.surface
    var dominantColor by remember {
        mutableStateOf(defaultDominantColor)
    }
    Column(
        modifier = modifier
            .shadow(elevation = 5.dp, shape = RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .aspectRatio(1f)
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        dominantColor,
                        defaultDominantColor
                    )
                )
            )
            .clickable {
                navController.navigate(
                    route = "pokemon_details/${dominantColor}/${pokemonItemView.pokemonName}"
                )
            },
        horizontalAlignment = CenterHorizontally
    ) {
        SubcomposeAsyncImage(
            modifier = Modifier
                .size(120.dp)
                .align(CenterHorizontally),
            model = pokemonItemView.imageURL,
            contentDescription = "",
            loading = {
                CircularProgressIndicator(
                    modifier = Modifier.scale(0.5f),
                    color = MaterialTheme.colors.primary
                )
            },
            onSuccess = {
                viewModel.calculateDominantColor(drawable = it.result.drawable) { color ->
                    dominantColor = color
                }
            }
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = pokemonItemView.pokemonName,
            fontFamily = RobotoCondensed,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
    }
}
