package dev.marcosfarias.pokedex.ui.dashboard.evolution

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dev.marcosfarias.pokedex.R
import dev.marcosfarias.pokedex.ui.dashboard.DashboardViewModel
import dev.marcosfarias.pokedex.utils.BundleKeyUtil
import kotlinx.android.synthetic.main.fragment_evolution.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class EvolutionFragment : Fragment(R.layout.fragment_evolution) {

    companion object {
        @JvmStatic
        fun newInstance(id: Int) = EvolutionFragment().apply {
            arguments = Bundle().apply {
                putInt("id", id)
            }
        }
    }

    private val dashboardViewModel: DashboardViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_evolution, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = checkNotNull(arguments?.getInt(BundleKeyUtil.ID))

        val layoutManager = LinearLayoutManager(context)
        val pokemonEvolutionRecyclerView = recyclerViewEvolvingPokemon
        pokemonEvolutionRecyclerView.layoutManager = layoutManager
        val pokemonEvolutionAdapter = EvolutionAdapter(view.context)
        pokemonEvolutionRecyclerView.adapter = pokemonEvolutionAdapter

        dashboardViewModel.getPokemonById(id).observe(viewLifecycleOwner, Observer { pokemonValue ->
            pokemonValue?.let { pokemon ->

                pokemonEvolutionAdapter.setPokemonInList(pokemon)
                val evolutions = pokemon.species.evolutionChain.chain.evolvesTo

                dashboardViewModel.getEvolutionsByChainLink(evolutions.first()).observe(viewLifecycleOwner, Observer { pokemonValue ->
                    pokemonValue?.let { pokemon ->
                        pokemonEvolutionAdapter.setPokemonInList(pokemon)
                    }
                })
            }
        })
    }
}
