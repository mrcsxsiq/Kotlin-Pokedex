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
    suspend fun getById(id: Int): Pokemon

    @Query("SELECT * FROM pokemon WHERE id > :offset")
    suspend fun getByRangeOffsetId(offset: Int) : MutableList<Pokemon>

    @Query("SELECT * FROM pokemon")
    suspend fun getAll(): MutableList<Pokemon>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(pokemon: Pokemon)

    @Query("DELETE FROM pokemon")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(model: Pokemon)
}
