package br.com.pokedex.android.data.service.responses

data class Move(
    val move: MoveX,
    val version_group_details: List<VersionGroupDetail>
)