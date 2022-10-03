package br.com.pokedex.android.data.service.responses

import com.squareup.moshi.Json

data class GenerationIv(
    @field:Json(name = "diamond-pearl") val diamondPearl: DiamondPearl,
    @field:Json(name = "heartgold-soulsilver") val heartGoldSoulSilver: HeartgoldSoulsilver,
    val platinum: Platinum
)