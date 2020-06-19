package dev.marcosfarias.pokedex.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.marcosfarias.pokedex.database.dao.EvolutionChainDAO
import dev.marcosfarias.pokedex.database.dao.PokemonDAO
import dev.marcosfarias.pokedex.database.dao.SpeciesDAO
import dev.marcosfarias.pokedex.model.EvolutionChain
import dev.marcosfarias.pokedex.model.Pokemon
import dev.marcosfarias.pokedex.model.Species

@Database(entities = [Pokemon::class, Species::class, EvolutionChain::class], version = 5, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun pokemonDAO(): PokemonDAO
    abstract fun speciesDAO(): SpeciesDAO
    abstract fun evolutionChainDAO(): EvolutionChainDAO
}
