package dev.marcosfarias.pokedex.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.marcosfarias.pokedex.model.Species

@Dao
interface SpeciesDAO {

    @Query("SELECT * FROM species WHERE id = :id")
    suspend fun get(id: Int): Species?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(species: Species)
}