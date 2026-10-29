package com.example.pokedex.unit

import org.junit.Assert.assertEquals
import org.junit.Test

class PokemonSearchTest {

    private fun search(
        names: List<String>,
        query: String
    ): List<String> {
        if (query.isBlank()) return names

        return names.filter {
            it.contains(query.trim(), ignoreCase = true)
        }
    }

    private val names = listOf(
        "pikachu",
        "bulbasaur",
        "raichu"
    )

    @Test
    fun searchByName_returnsMatchingPokemon() {
        assertEquals(
            listOf("pikachu"),
            search(names, "PIKA")
        )
    }

    @Test
    fun searchByPartialName_returnsMatches() {
        assertEquals(
            listOf("pikachu", "raichu"),
            search(names, "chu")
        )
    }

    @Test
    fun searchWithNoMatches_returnsEmptyList() {
        assertEquals(
            emptyList<String>(),
            search(names, "mewtwo")
        )
    }
}