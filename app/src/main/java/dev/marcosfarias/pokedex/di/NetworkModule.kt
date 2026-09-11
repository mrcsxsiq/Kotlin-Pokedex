package dev.marcosfarias.pokedex.di

import dev.marcosfarias.pokedex.repository.PokemonService
import dev.marcosfarias.pokedex.utils.AppConnectivityManager
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

val networkModule = module {
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/rodrigosambadesaa/Kotlin-Pokedex/master/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>().create<PokemonService>()
    }

    single {
        AppConnectivityManager()
    }
}

