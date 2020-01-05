package dev.marcosfarias.pokedex.ui.generation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dev.marcosfarias.pokedex.R
import dev.marcosfarias.pokedex.adapter.GenerationAdapter
import dev.marcosfarias.pokedex.model.Generation
import kotlinx.android.synthetic.main.fragment_generation.view.*

class GenerationFragment : BottomSheetDialogFragment() {

    private lateinit var generationViewModel: GenerationViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ) : View? {
        generationViewModel = ViewModelProviders.of(this).get(GenerationViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_generation, container, false)
        val recyclerView = root.recyclerView
        val layoutManager = GridLayoutManager(context, 2)
        recyclerView.layoutManager = layoutManager

        generationViewModel.getListGeneration().observe(this, Observer  {
            recyclerView.adapter = GenerationAdapter(it, root.context)
        })
        return root
    }

}