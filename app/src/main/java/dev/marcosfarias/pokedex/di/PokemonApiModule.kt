package dev.marcosfarias.pokedex.di

import me.sargunvohra.lib.pokekotlin.client.RxPokeApi
import me.sargunvohra.lib.pokekotlin.client.RxPokeApiClient
import org.koin.dsl.module

val pokemonApiModule = module {
    single {
        RxPokeApiClient()
    }

    single {
        get<RxPokeApi>()
    }
}