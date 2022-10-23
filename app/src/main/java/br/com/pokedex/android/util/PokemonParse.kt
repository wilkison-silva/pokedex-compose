package br.com.pokedex.android.util

import androidx.compose.ui.graphics.Color
import br.com.pokedex.android.data.service.responses.Stat
import br.com.pokedex.android.data.service.responses.Type
import br.com.pokedex.android.presentation.ui.theme.AtkColor
import br.com.pokedex.android.presentation.ui.theme.DefColor
import br.com.pokedex.android.presentation.ui.theme.HPColor
import br.com.pokedex.android.presentation.ui.theme.SpAtkColor
import br.com.pokedex.android.presentation.ui.theme.SpDefColor
import br.com.pokedex.android.presentation.ui.theme.SpdColor
import br.com.pokedex.android.presentation.ui.theme.TypeBug
import br.com.pokedex.android.presentation.ui.theme.TypeDark
import br.com.pokedex.android.presentation.ui.theme.TypeDragon
import br.com.pokedex.android.presentation.ui.theme.TypeElectric
import br.com.pokedex.android.presentation.ui.theme.TypeFairy
import br.com.pokedex.android.presentation.ui.theme.TypeFighting
import br.com.pokedex.android.presentation.ui.theme.TypeFire
import br.com.pokedex.android.presentation.ui.theme.TypeFlying
import br.com.pokedex.android.presentation.ui.theme.TypeGhost
import br.com.pokedex.android.presentation.ui.theme.TypeGrass
import br.com.pokedex.android.presentation.ui.theme.TypeGround
import br.com.pokedex.android.presentation.ui.theme.TypeIce
import br.com.pokedex.android.presentation.ui.theme.TypeNormal
import br.com.pokedex.android.presentation.ui.theme.TypePoison
import br.com.pokedex.android.presentation.ui.theme.TypePsychic
import br.com.pokedex.android.presentation.ui.theme.TypeRock
import br.com.pokedex.android.presentation.ui.theme.TypeSteel
import br.com.pokedex.android.presentation.ui.theme.TypeWater

fun parseTypeToColor(type: Type): Color {
    return when(type.type.name.lowercase()) {
        "normal" -> TypeNormal
        "fire" -> TypeFire
        "water" -> TypeWater
        "electric" -> TypeElectric
        "grass" -> TypeGrass
        "ice" -> TypeIce
        "fighting" -> TypeFighting
        "poison" -> TypePoison
        "ground" -> TypeGround
        "flying" -> TypeFlying
        "psychic" -> TypePsychic
        "bug" -> TypeBug
        "rock" -> TypeRock
        "ghost" -> TypeGhost
        "dragon" -> TypeDragon
        "dark" -> TypeDark
        "steel" -> TypeSteel
        "fairy" -> TypeFairy
        else -> Color.Black
    }
}

fun parseStatToColor(stat: Stat): Color {
    return when(stat.stat.name.lowercase()) {
        "hp" -> HPColor
        "attack" -> AtkColor
        "defense" -> DefColor
        "special-attack" -> SpAtkColor
        "special-defense" -> SpDefColor
        "speed" -> SpdColor
        else -> Color.White
    }
}

fun parseStatToAbbr(stat: Stat): String {
    return when(stat.stat.name.lowercase()) {
        "hp" -> "HP"
        "attack" -> "Atk"
        "defense" -> "Def"
        "special-attack" -> "SpAtk"
        "special-defense" -> "SpDef"
        "speed" -> "Spd"
        else -> ""
    }
}