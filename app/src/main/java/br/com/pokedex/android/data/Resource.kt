package br.com.pokedex.android.data

sealed class Resource {
    data class Sucess(val data: Any) : Resource()
    object Error : Resource()
}
