package dev.marcosfarias.pokedex.ui.dashboard.stats

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import dev.marcosfarias.pokedex.R
import dev.marcosfarias.pokedex.ui.dashboard.DashboardViewModel
import kotlinx.android.synthetic.main.fragment_stats.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class StatsFragment private constructor() : Fragment(R.layout.fragment_stats) {

    constructor(id: String) : this() {
        arguments = Bundle().apply {
            putString("id", id)
        }
    }

    private val dashboardViewModel: DashboardViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = checkNotNull(arguments?.getString("id"))
        dashboardViewModel.getPokemonById(id).observe(viewLifecycleOwner, Observer { pokemonValue ->
            pokemonValue?.let { pokemon ->
//                TODO: Fazer carregamento de Ydescription?
//                textViewTypeDefenses.text = pokemon.ydescription
//
//                TODO: Fazer carregamento de Stats
//                textViewHP.text = pokemon.hp.toString()
//                textViewAttack.text = pokemon.attack.toString()
//                textViewDefense.text = pokemon.defense.toString()
//                textViewSpAtk.text = pokemon.specialAttack.toString()
//                textViewSpDef.text = pokemon.specialDefense.toString()
//                textViewSpeed.text = pokemon.speed.toString()
//                textViewTotal.text = pokemon.total.toString()
//
//                progressBarHP.progress = pokemon.hp ?: 0
//                progressBarAttack.progress = pokemon.attack ?: 0
//                progressBarDefense.progress = pokemon.defense ?: 0
//                progressBarSpAtk.progress = pokemon.specialAttack ?: 0
//                progressBarSpDef.progress = pokemon.specialDefense ?: 0
//                progressBarSpeed.progress = pokemon.speed ?: 0
//                progressBarTotal.progress = pokemon.total ?: 0
            }
        })
    }
}
