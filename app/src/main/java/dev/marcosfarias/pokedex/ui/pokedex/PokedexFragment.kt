package dev.marcosfarias.pokedex.ui.pokedex

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.leinardi.android.speeddial.SpeedDialView
import dev.marcosfarias.pokedex.R
import dev.marcosfarias.pokedex.ui.generation.GenerationFragment
import dev.marcosfarias.pokedex.ui.search.SearchFragment
import dev.marcosfarias.pokedex.utils.PokemonColorUtil
import kotlinx.android.synthetic.main.fragment_pokedex.*
import kotlinx.android.synthetic.main.item_pokemon.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PokedexFragment : Fragment() {

    private val pokedexViewModel: PokedexViewModel by viewModel()
    private var selectedPokemonId: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (selectedPokemonId != null) {
            postponeEnterTransition()
        }

        return inflater.inflate(R.layout.fragment_pokedex, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.window?.statusBarColor =
            PokemonColorUtil(view.context).convertColor(R.color.background)

        val progressBar = progressBar
        val recyclerView = recyclerView
        val layoutManager = GridLayoutManager(context, 2)
        recyclerView.layoutManager = layoutManager

        pokedexViewModel.getListPokemon().observe(viewLifecycleOwner, Observer { pokemons ->
            recyclerView.adapter = PokemonAdapter(
                list = pokemons,
                itemClickedListener = { pokemon, viewHolder ->
                    selectedPokemonId = pokemon.id

                    val extras = FragmentNavigatorExtras(
                        viewHolder.itemView.imageView to viewHolder.itemView.imageView.transitionName
                    )

                    val bundle = bundleOf(
                        "id" to pokemon.id,
                        "name" to pokemon.name
                    )

                    findNavController()
                        .navigate(
                            R.id.action_navigation_pokedex_to_navigation_dashboard,
                            bundle,
                            null,
                            extras
                        )
                },
                imageLoadedListener = { pokemon, _ ->
                    if (pokemon.id == selectedPokemonId) {
                        startPostponedEnterTransition()
                    }
                })
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
        dialog.show(requireFragmentManager(), "")
    }

    private fun showSearch() {
        val dialog = SearchFragment()
        dialog.show(requireFragmentManager(), "")
    }
}
