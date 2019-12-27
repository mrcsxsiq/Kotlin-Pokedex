package dev.marcosfarias.pokedex

import android.app.Application
import android.content.Context
import androidx.room.Room
import dev.marcosfarias.pokedex.database.AppDatabase

class App : Application() {
    companion object {
        var context: Context? = null
        var database: AppDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        context = this

        database = Room.databaseBuilder(
            this,
                AppDatabase::class.java,
                getString(R.string.app_name)
            )
            .fallbackToDestructiveMigration()
            .build()

    }
}