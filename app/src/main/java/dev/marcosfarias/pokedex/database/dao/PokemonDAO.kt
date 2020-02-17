package dev.marcosfarias.pokedex.database.dao

import androidx.room.*
import dev.marcosfarias.pokedex.model.Pokemon

@Dao
interface PokemonDAO {

    @Query("SELECT * FROM pokemon WHERE id = :id")
    suspend fun getById(id: String): Pokemon

    @Query("SELECT * FROM pokemon")
    suspend fun all(): List<Pokemon>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(pokemon: List<Pokemon>)

    @Query("DELETE FROM pokemon")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(model: Pokemon)
}
