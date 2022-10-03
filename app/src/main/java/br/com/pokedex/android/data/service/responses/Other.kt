package br.com.pokedex.android.data.service.responses

import com.squareup.moshi.Json

data class Other(
    val dream_world: DreamWorld,
    val home: Home,
    @field:Json(name = "official-artwork") val officialArtwork: OfficialArtwork
)