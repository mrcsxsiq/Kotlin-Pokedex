package dev.marcosfarias.pokedex.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.marcosfarias.pokedex.model.Pokemon

@Dao
interface PokemonDAO {

    @Query("SELECT * FROM pokemon WHERE id = :id")
    suspend fun getById(id: String): Pokemon

    @Query("SELECT * FROM pokemon WHERE id > :offset")
    suspend fun getByRangeOffsetId(offset: String) : MutableList<Pokemon>

    @Query("SELECT * FROM pokemon")
    suspend fun all(): MutableList<Pokemon>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(pokemon: Pokemon)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAll(pokemonList: MutableList<Pokemon>)

    @Query("DELETE FROM pokemon")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(model: Pokemon)
}
