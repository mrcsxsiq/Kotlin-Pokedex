package dev.marcosfarias.pokedex.model

data class PokemonResult(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<PokemonName>
)