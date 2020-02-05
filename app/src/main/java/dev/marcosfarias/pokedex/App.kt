package dev.marcosfarias.pokedex

import android.app.Application
import android.content.Context
import androidx.room.Room
import dev.marcosfarias.pokedex.database.AppDatabase
import dev.marcosfarias.pokedex.di.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {


    override fun onCreate() {
        super.onCreate()
        configureDI()

    }

    private fun configureDI() = startKoin {
        androidContext(this@App)
        modules(appComponent)
    }

}
