package dev.marcosfarias.pokedex

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import dev.marcosfarias.pokedex.database.dao.PokemonDAO
import dev.marcosfarias.pokedex.model.Pokemon
import dev.marcosfarias.pokedex.repository.PokemonService
import dev.marcosfarias.pokedex.ui.pokedex.PokedexViewModel
import dev.marcosfarias.pokedex.utils.AppConnectivityManager
import io.mockk.*
import org.junit.*
import java.net.UnknownHostException

class PokedexViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val dao: PokemonDAO = mockk(relaxed = true)
    private val service: PokemonService = mockk(relaxed = true)
    private val connectivityManager: AppConnectivityManager = mockk(relaxed = true)
    private lateinit var viewModel: PokedexViewModel

    @Before
    fun before() {
        viewModel = PokedexViewModel(dao, service, connectivityManager)
    }

    @Test
    fun `GIVEN viewmodel WHEN created THEN does not start network request`() {
        verify(exactly = 0) { service.get() }
    }

    @Test
    fun `GIVEN unknown host WHEN classified THEN is a network failure`() {
        Assert.assertTrue(AppConnectivityManager().isNetworkFailure(UnknownHostException()))
    }

    @Test
    fun `GIVEN application error WHEN classified THEN is not a network failure`() {
        Assert.assertFalse(AppConnectivityManager().isNetworkFailure(IllegalStateException()))
    }

    @Test
    fun `GIVEN dangling VPN WHEN checking policy THEN remote request is blocked`() {
        Assert.assertFalse(AppConnectivityManager().canStartRemoteRequest(true, false))
    }

    @Test
    fun `GIVEN mocked dao results WHEN get list of pokemons from view model THEN result as expected`() {
        // GIVEN
        val expected = listOf(
            Pokemon().apply { name = "Psyduck" },
            Pokemon().apply { name = "Onyx" }
        )
        every { dao.all() } returns MutableLiveData(expected)

        // WHEN
        val result = viewModel.getListPokemon()

        // THEN
        Assert.assertEquals(expected, result.value!!)
    }

    companion object {
        @JvmStatic
        @AfterClass
        fun tearDown() {
            unmockkAll()
        }
    }
}
