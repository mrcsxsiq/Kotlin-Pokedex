package dev.marcosfarias.pokedex.repository

import dev.marcosfarias.pokedex.model.Pokemon
import retrofit2.Response
import retrofit2.http.GET

interface PokemonService {
    @GET("pokemon.json")
    suspend fun get(): Response<List<Pokemon>>
}
