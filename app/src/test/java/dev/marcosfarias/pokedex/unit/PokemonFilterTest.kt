package com.example.pokedex.unit

import org.junit.Assert.assertEquals
import org.junit.Test

class PokemonFilterTest {

    data class Pokemon(
        val name: String,
        val generation: Int
    )

    @Test
    fun filterByGeneration_returnsOnlySelectedGeneration() {
        val pokemons = listOf(
            Pokemon("bulbasaur", 1),
            Pokemon("pikachu", 1),
            Pokemon("chikorita", 2)
        )

        val result = pokemons.filter {
            it.generation == 2
        }

        assertEquals(
            listOf("chikorita"),
            result.map { it.name }
        )
    }

    @Test
    fun filterByUnknownGeneration_returnsEmptyList() {
        val pokemons = listOf(
            Pokemon("pikachu", 1)
        )

        val result = pokemons.filter {
            it.generation == 99
        }

        assertEquals(emptyList<Pokemon>(), result)
    }
}