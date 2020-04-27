package dev.marcosfarias.pokedex.di

import dev.marcosfarias.pokedex.ui.pokedex.PokedexRepository
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient
import org.koin.dsl.module

val pokeApiModule = module {
    single {
        PokeApiClient()
    }

    factory { PokedexRepository(get(), get()) }
}