package br.com.pokedex.android.data.service.responses

import com.squareup.moshi.Json

data class Versions(
    @field:Json(name = "generation-i") val generationI: GenerationI,
    @field:Json(name = "generation-ii") val generationII: GenerationIi,
    @field:Json(name = "generation-iii") val generationIII: GenerationIii,
    @field:Json(name = "generation-iv") val generationIV: GenerationIv,
    @field:Json(name = "generation-v") val generationV: GenerationV,
    @field:Json(name = "generation-vi") val generationVI: GenerationVi,
    @field:Json(name = "generation-vii") val generationVII: GenerationVii
)