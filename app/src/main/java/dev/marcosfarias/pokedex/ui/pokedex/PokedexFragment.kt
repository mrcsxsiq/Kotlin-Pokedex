package dev.marcosfarias.pokedex.ui.pokedex

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.leinardi.android.speeddial.SpeedDialView
import dev.marcosfarias.pokedex.R
import dev.marcosfarias.pokedex.model.Pokemon
import dev.marcosfarias.pokedex.ui.generation.GenerationFragment
import dev.marcosfarias.pokedex.ui.search.SearchFragment
import dev.marcosfarias.pokedex.utils.PokemonColorUtil
import kotlinx.android.synthetic.main.fragment_pokedex.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PokedexFragment : Fragment(R.layout.fragment_pokedex) {

    private val pokedexViewModel: PokedexViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.window?.statusBarColor =
            PokemonColorUtil(view.context).convertColor(R.color.background)

        val progressBar = progressBar
        val recyclerView = recyclerView
        val layoutManager = GridLayoutManager(context, 2)
        recyclerView.layoutManager = layoutManager

        pokedexViewModel.getListPokemon().observe(viewLifecycleOwner, Observer {
            val pokemons: List<Pokemon> = it
            recyclerView.adapter = PokemonAdapter(pokemons)
            if (pokemons.isNotEmpty())
                progressBar.visibility = View.GONE
        })

        val speedDialView = speedDial
        speedDialView.inflate(R.menu.menu_pokedex)
        speedDialView.setOnActionSelectedListener(SpeedDialView.OnActionSelectedListener { actionItem ->
            when (actionItem.id) {
                R.id.menuAllGen -> {
                    showAllGen()
                    speedDialView.close()
                    return@OnActionSelectedListener true
                }
                R.id.menuSearch -> {
                    showSearch()
                    speedDialView.close()
                    return@OnActionSelectedListener true
                }
                else -> {
                    speedDialView.close()
                    return@OnActionSelectedListener true
                }
            }
        })
    }

    private fun showAllGen() {
        val dialog = GenerationFragment()
        dialog.show(parentFragmentManager, "")
    }

    private fun showSearch() {
        val dialog = SearchFragment()
        dialog.show(parentFragmentManager, "")
    }
}
