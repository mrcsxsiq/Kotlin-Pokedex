package dev.marcosfarias.pokedex.ui.generation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dev.marcosfarias.pokedex.R
import dev.marcosfarias.pokedex.model.Generation
import kotlinx.android.synthetic.main.fragment_generation.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class GenerationFragment : BottomSheetDialogFragment() {

    private val generationViewModel: GenerationViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_generation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = recyclerView
        val layoutManager = GridLayoutManager(context, 2)
        recyclerView.layoutManager = layoutManager

        generationViewModel.getListGeneration().observe(viewLifecycleOwner, Observer {
            val pokemons: List<Generation> = it
            recyclerView.adapter = GenerationAdapter(pokemons, view.context)
        })
    }
}
