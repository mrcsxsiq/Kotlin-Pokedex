package dev.marcosfarias.pokedex.di

import dev.marcosfarias.pokedex.repository.DashboardRepository
import dev.marcosfarias.pokedex.repository.PokedexRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory {
        PokedexRepository(get(), get())
    }

    factory {
        DashboardRepository(get(), get(), get(), get())
    }
}