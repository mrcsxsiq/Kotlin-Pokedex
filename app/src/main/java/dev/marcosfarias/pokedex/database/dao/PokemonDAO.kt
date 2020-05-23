package dev.marcosfarias.pokedex.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.marcosfarias.pokedex.model.Pokemon

@Dao
interface PokemonDAO {

    @Query("SELECT * FROM pokemon WHERE id = :id")
    fun getById(id: String?): LiveData<Pokemon>

    @Query("SELECT * FROM pokemon WHERE id IN(:evolutionIds)")
    fun getEvolutionsByIds(evolutionIds: List<String>): LiveData<List<Pokemon>>

    @Query("SELECT * FROM pokemon")
    fun all(): LiveData<List<Pokemon>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(pokemon: List<Pokemon>)

    @Query("DELETE FROM pokemon")
    fun deleteAll()

    @Delete
    fun delete(model: Pokemon)
}
