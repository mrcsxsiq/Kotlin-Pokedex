package dev.marcosfarias.pokedex.di

import androidx.room.Room
import dev.marcosfarias.pokedex.database.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            "rapidsend.db"
        ).build()
    }

    single {
        get<AppDatabase>().pokemonDAO()
    }
}