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
                //                TODO: Fazer carregamento de About

//                textViewDescription.text = pokemon.xdescription
                textViewHeight.text = pokemon.height.toString()
                textViewWeight.text = pokemon.weight.toString()
//                textViewEggCycle.text = pokemon.cycles
//                textViewEggGroups.text = pokemon.eggGroups
                textViewBaseEXP.text = pokemon.baseExperience.toString()
            }
        })
    }
}
