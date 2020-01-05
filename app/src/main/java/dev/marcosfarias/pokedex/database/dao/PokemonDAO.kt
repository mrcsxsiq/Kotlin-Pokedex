package dev.marcosfarias.pokedex.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import dev.marcosfarias.pokedex.model.Pokemon

@Dao
interface PokemonDAO {

    @Query("SELECT * FROM pokemon WHERE id = :id")
    fun getById(id: String?): LiveData<Pokemon?>

    @Query("SELECT * FROM pokemon")
    fun all(): LiveData<List<Pokemon>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun add(vararg pokemon: Pokemon)

    @Query("DELETE FROM pokemon")
    fun deleteAll()

    @Delete
    fun delete(model: Pokemon)

}