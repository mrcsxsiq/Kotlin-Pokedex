package dev.marcosfarias.pokedex.ui.dashboard.about

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import dev.marcosfarias.pokedex.R
import dev.marcosfarias.pokedex.ui.dashboard.DashboardViewModel
import kotlinx.android.synthetic.main.fragment_about.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AboutFragment private constructor() : Fragment(R.layout.fragment_about) {
    private val dashboardViewModel: DashboardViewModel by viewModel()

    constructor(id: String) : this() {
        arguments = Bundle().apply {
            putString("id", id)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = checkNotNull(arguments?.getString("id"))
        dashboardViewModel.getPokemonById(id).observe(viewLifecycleOwner, Observer { pokemonValue ->
            pokemonValue?.let { pokemon ->
                textViewDescription.text = pokemon.xdescription
                textViewHeight.text = pokemon.height
                textViewWeight.text = pokemon.weight
                textViewEggCycle.text = pokemon.cycles
                textViewEggGroups.text = pokemon.eggGroups
                textViewBaseEXP.text = pokemon.baseExp
            }
        })
    }
}
