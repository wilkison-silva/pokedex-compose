package br.com.pokedex.android.data.service.responses

import com.squareup.moshi.Json

data class GenerationVii(
    val icons: Icons,
    @field:Json(name = "ultra-sun-ultra-moon") val ultraSunUltraMoon: UltraSunUltraMoon
)