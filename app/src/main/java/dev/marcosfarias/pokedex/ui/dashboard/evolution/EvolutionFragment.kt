package dev.marcosfarias.pokedex.ui.dashboard.evolution

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dev.marcosfarias.pokedex.R
import dev.marcosfarias.pokedex.model.Pokemon
import dev.marcosfarias.pokedex.ui.dashboard.DashboardViewModel
import kotlinx.android.synthetic.main.fragment_evolution.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class EvolutionFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance(id: String?) = EvolutionFragment().apply {
            arguments = Bundle().apply {
                putString("id", id)
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

        val id = checkNotNull(arguments?.getString("id"))
        val recyclerView = recyclerViewEvolvingPokemon
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        val adapter = EvolutionAdapter(view.context)
        recyclerView.adapter = adapter

        dashboardViewModel.getPokemonById(id).observe(viewLifecycleOwner, Observer { pokemonValue ->
            pokemonValue?.let { pokemon ->
                val evolutions = pokemon.evolutions ?: emptyList()
                dashboardViewModel.getPokemonEvolutionsByIds(evolutions).observe(viewLifecycleOwner, Observer {
                    val pokemons: List<Pokemon> = it
                    adapter.setList(pokemons)
                    adapter.notifyDataSetChanged()

                    if (pokemons.isEmpty())
                        textNonEvolving.visibility = View.VISIBLE
                })
            }
        })
    }
}
