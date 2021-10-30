package dev.marcosfarias.pokedex

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dev.marcosfarias.pokedex.model.Generation
import dev.marcosfarias.pokedex.ui.generation.GenerationViewModel
import io.mockk.spyk
import io.mockk.unmockkAll
import org.junit.*

class GenerationViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: GenerationViewModel

    @Before
    fun before() {
        viewModel = spyk(GenerationViewModel(), recordPrivateCalls = true)
    }

    @Test
    fun `GIVEN list generation WHEN call function to get generations THEN result as expected`() {
        // GIVEN
        val expected = listOf(
            Generation(id = 1, title = R.string.generation_item_1, image = R.drawable.gen1),
            Generation(id = 1, title = R.string.generation_item_2, image = R.drawable.gen2),
            Generation(id = 1, title = R.string.generation_item_3, image = R.drawable.gen3),
            Generation(id = 1, title = R.string.generation_item_4, image = R.drawable.gen4),
            Generation(id = 1, title = R.string.generation_item_5, image = R.drawable.gen5),
            Generation(id = 1, title = R.string.generation_item_6, image = R.drawable.gen6),
            Generation(id = 1, title = R.string.generation_item_7, image = R.drawable.gen7),
            Generation(id = 1, title = R.string.generation_item_8, image = R.drawable.gen8)
        )

        // WHEN
        val result = viewModel.getListGeneration()

        // THEN
        val expectedTransformed = expected.map { it.toString() }
        val resultTransformed = result.value!!.map { it.toString() }
        Assert.assertEquals(expectedTransformed, resultTransformed)
    }

    companion object {
        @JvmStatic
        @AfterClass
        fun tearDown() {
            unmockkAll()
        }
    }
}
