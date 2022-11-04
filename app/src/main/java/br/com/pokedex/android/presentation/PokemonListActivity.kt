package br.com.pokedex.android.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.toLowerCase
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.pokedex.android.presentation.pokemon_details.PokemonDetailsScreen
import br.com.pokedex.android.presentation.pokemon_list.components.PokemonListScreen
import br.com.pokedex.android.presentation.ui.theme.PokedexTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class PokemonListActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                     startDestination = "pokemon_list_screen"
                ) {
                    composable(route = "pokemon_list_screen") {
                        PokemonListScreen(navController = navController)
                    }
                    composable(
                        route = "pokemon_details/{dominantColor}/{pokemonName}",
                        arguments = listOf(
                            navArgument(name = "dominantColor") {
                                type = NavType.IntType
                            },
                            navArgument(name = "pokemonName") {
                                type = NavType.StringType
                            }
                        )
                    ) {
                        val dominantColor = remember {
                            val color = it.arguments?.getInt("dominantColor")
                            color?.let {
                                Color(it)
                            } ?: Color.White
                        }
                        val pokemonName = remember {
                            it.arguments?.getString("pokemonName")
                        }
                        PokemonDetailsScreen(
                            dominantColor = dominantColor,
                            pokemonName = pokemonName?.lowercase() ?: "",
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}