package dev.marcosfarias.pokedex.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.marcosfarias.pokedex.model.Pokemon

@Dao
interface PokemonDAO {

    @Query("SELECT * FROM pokemon WHERE id = :id")
    suspend fun get(id: Int): Pokemon

    @Query("SELECT * FROM pokemon WHERE id > :offset")
    suspend fun getRange(offset: Int) : MutableList<Pokemon>

    @Query("SELECT * FROM pokemon")
    suspend fun getAll(): MutableList<Pokemon>

    @Query("SELECT * FROM pokemon WHERE id IN (:idList)")
    suspend fun getList(idList: MutableList<Int>): MutableList<Pokemon>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(pokemon: Pokemon)
}
