package br.com.pokedex.android.data.service.responses

import com.squareup.moshi.Json

data class GenerationV(
    @field:Json(name = "black-white") val blackWhite: BlackWhite
)