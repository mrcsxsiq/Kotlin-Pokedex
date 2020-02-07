package dev.marcosfarias.pokedex.ui.generation

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.marcosfarias.pokedex.R
import dev.marcosfarias.pokedex.model.Generation

class GenerationViewModel(private val context: Context) : ViewModel() {

    private val listGeneration = MutableLiveData<List<Generation>>()

    fun getListGeneration(): LiveData<List<Generation>> {
        listGeneration.value = listOf(
            Generation(
                1,
                context.resources.getString(R.string.generation_item_1),
                R.drawable.gen1
            ),
            Generation(
                1,
                context.resources.getString(R.string.generation_item_2),
                R.drawable.gen2
            ),
            Generation(
                1,
                context.resources.getString(R.string.generation_item_3),
                R.drawable.gen3
            ),
            Generation(
                1,
                context.resources.getString(R.string.generation_item_4),
                R.drawable.gen4
            ),
            Generation(
                1,
                context.resources.getString(R.string.generation_item_5),
                R.drawable.gen5
            ),
            Generation(
                1,
                context.resources.getString(R.string.generation_item_6),
                R.drawable.gen6
            ),
            Generation(
                1,
                context.resources.getString(R.string.generation_item_7),
                R.drawable.gen7
            ),
            Generation(
                1,
                context.resources.getString(R.string.generation_item_8),
                R.drawable.gen8
            )
        )
        return listGeneration
    }
}
