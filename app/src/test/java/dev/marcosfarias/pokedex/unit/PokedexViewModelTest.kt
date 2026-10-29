package dev.marcosfarias.pokedex.unit

import androidx.lifecycle.MutableLiveData
import dev.marcosfarias.pokedex.database.dao.PokemonDAO
import dev.marcosfarias.pokedex.model.Pokemon
import dev.marcosfarias.pokedex.repository.PokemonService
import dev.marcosfarias.pokedex.ui.pokedex.PokedexViewModel
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.Runs
import io.mockk.slot
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class PokedexViewModelTest {

    private lateinit var dao: PokemonDAO
    private lateinit var service: PokemonService
    private lateinit var call: Call<List<Pokemon>>
    private lateinit var callbackSlot: io.mockk.CapturingSlot<Callback<List<Pokemon>>>

    @Before
    fun setUp() {
        dao = mockk()
        service = mockk()
        call = mockk()
        callbackSlot = slot()

        every { service.get() } returns call
        every { call.enqueue(capture(callbackSlot)) } just Runs
        every { dao.all() } returns MutableLiveData(emptyList())
    }

    @Test
    fun `viewModel starts network request`() {
        PokedexViewModel(dao, service)

        verify(exactly = 1) { service.get() }
        verify(exactly = 1) { call.enqueue(any()) }
    }

    @Test
    fun `getListPokemon returns dao live data`() {
        val expected = listOf(Pokemon(id = "25", name = "pikachu"))
        val liveData = MutableLiveData(expected)
        every { dao.all() } returns liveData

        val viewModel = PokedexViewModel(dao, service)

        assertEquals(expected, viewModel.getListPokemon().value)
    }

    @Test
    fun `successful response saves pokemons to dao`() {
        val expected = listOf(Pokemon(id = "25", name = "pikachu"))
        val saved = CountDownLatch(1)

        every { dao.add(expected) } answers {
            saved.countDown()
        }

        PokedexViewModel(dao, service)

        callbackSlot.captured.onResponse(
            call,
            Response.success(expected)
        )

        assertEquals(
            true,
            saved.await(2, TimeUnit.SECONDS)
        )
        verify(exactly = 1) { dao.add(expected) }
    }

    @Test
    fun `network failure does not save pokemons`() {
        PokedexViewModel(dao, service)

        callbackSlot.captured.onFailure(
            call,
            IOException("Network error")
        )

        verify(exactly = 0) { dao.add(any()) }
    }

    @Test
    fun `server error response does not save pokemons`() {
        PokedexViewModel(dao, service)

        val errorBody = """{"error":"server error"}"""
            .toResponseBody("application/json".toMediaType())

        callbackSlot.captured.onResponse(
            call,
            Response.error<List<Pokemon>>(500, errorBody)
        )

        verify(exactly = 0) { dao.add(any()) }
    }
}
