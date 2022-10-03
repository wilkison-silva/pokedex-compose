package br.com.pokedex.android.data.service.responses

import com.squareup.moshi.Json

data class GenerationIii(
    val emerald: Emerald,
    @field:Json(name = "firered-leafgreen") val fireredLeafgreen: FireredLeafgreen,
    @field:Json(name = "ruby-sapphire") val rubySapphire: RubySapphire
)