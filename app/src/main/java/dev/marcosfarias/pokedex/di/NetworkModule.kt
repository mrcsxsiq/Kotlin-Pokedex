package dev.marcosfarias.pokedex.di

import com.google.gson.GsonBuilder
import dev.marcosfarias.pokedex.repository.PokemonService
import dev.marcosfarias.pokedex.ui.pokedex.PokedexRepository
import dev.marcosfarias.pokedex.utils.PokemonImageUtil
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    val pokeApiUrl = "https://pokeapi.co/api/v2/"

    single<Retrofit> {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        val gson = GsonBuilder().setLenient().create()

        Retrofit.Builder()
            .baseUrl(pokeApiUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient.build())
            .build()
    }

    single {
        get<Retrofit>().create(PokemonService::class.java)
    }

    factory { PokedexRepository(get(), get()) }
}
