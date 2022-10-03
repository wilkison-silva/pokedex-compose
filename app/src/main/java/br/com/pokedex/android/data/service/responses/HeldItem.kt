package br.com.pokedex.android.data.service.responses

data class HeldItem(
    val item: Item,
    val version_details: List<VersionDetail>
)