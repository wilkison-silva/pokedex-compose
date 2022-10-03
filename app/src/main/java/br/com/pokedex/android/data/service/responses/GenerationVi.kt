package br.com.pokedex.android.data.service.responses

import com.squareup.moshi.Json

data class GenerationVi(
    @field:Json(name = "omegaruby-alphasapphire") val omegaRubyAlphaSapphire: OmegarubyAlphasapphire,
    @field:Json(name = "x-y") val xy: XY
)