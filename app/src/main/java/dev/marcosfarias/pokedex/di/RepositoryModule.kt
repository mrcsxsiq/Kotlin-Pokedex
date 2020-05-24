package dev.marcosfarias.pokedex.di

import dev.marcosfarias.pokedex.ui.dashboard.DashboardRepository
import dev.marcosfarias.pokedex.ui.pokedex.PokedexRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory { PokedexRepository(get(), get()) }
    factory { DashboardRepository(get()) }
}