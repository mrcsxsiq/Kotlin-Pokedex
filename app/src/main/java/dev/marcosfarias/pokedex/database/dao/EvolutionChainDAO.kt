package dev.marcosfarias.pokedex.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.marcosfarias.pokedex.model.EvolutionChain

@Dao
interface EvolutionChainDAO {

    @Query("SELECT * FROM evolutionChain WHERE id = :id")
    suspend fun get(id: Int): EvolutionChain?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(species: EvolutionChain)
}