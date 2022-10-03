package br.com.pokedex.android.data.service.responses

import com.squareup.moshi.Json

data class GenerationI(
    @field:Json(name = "red-blue") val redBlue: RedBlue,
    val yellow: Yellow
)