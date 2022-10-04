package br.com.pokedex.android.data.service.responses


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenerationViii(
    val icons: IconsX
)