package dev.marcosfarias.pokedex.ui.pokedex

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.leinardi.android.speeddial.SpeedDialView
import dev.marcosfarias.pokedex.R
import dev.marcosfarias.pokedex.model.Pokemon
import dev.marcosfarias.pokedex.ui.generation.GenerationFragment
import dev.marcosfarias.pokedex.ui.search.SearchFragment
import dev.marcosfarias.pokedex.utils.PokemonColorUtil
import kotlinx.android.synthetic.main.fragment_pokedex.view.*

class PokedexFragment : Fragment() {

    private lateinit var pokedexViewModel: PokedexViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ) : View? {
        pokedexViewModel = ViewModelProviders.of(this).get(PokedexViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_pokedex, container, false)

        activity?.window?.statusBarColor = PokemonColorUtil(root.context).covertColor(R.color.background)

        val progressBar = root.progressBar
        val recyclerView = root.recyclerView
        val layoutManager = GridLayoutManager(context, 2)
        recyclerView.layoutManager = layoutManager

        pokedexViewModel.getListPokemon().observe(this, Observer  {
            val pokemons : List<Pokemon> = it
            recyclerView.adapter = PokemonAdapter(pokemons, root.context)
            if (pokemons.isNotEmpty())
                progressBar.visibility = View.GONE
        })

        val speedDialView = root.speedDial
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

        return root
    }

    private fun showAllGen() {
        val dialog = GenerationFragment()
        dialog.show(fragmentManager!!, "")

    }

    private fun showSearch() {
        val dialog = SearchFragment()
        dialog.show(fragmentManager!!, "")
    }

}