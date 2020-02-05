package dev.marcosfarias.pokedex.repository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object APIService {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://gist.githubusercontent.com/mrcsxsiq/b94dbe9ab67147b642baa9109ce16e44/raw/97811a5df2df7304b5bc4fbb9ee018a0339b8a38/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val pokemonService: PokemonService = retrofit.create()
}
