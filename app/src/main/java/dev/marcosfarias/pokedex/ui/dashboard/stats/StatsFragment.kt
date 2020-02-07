package dev.marcosfarias.pokedex.ui.dashboard.stats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import dev.marcosfarias.pokedex.R
import dev.marcosfarias.pokedex.ui.dashboard.DashboardViewModel
import kotlinx.android.synthetic.main.fragment_stats.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class StatsFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance(id: String?) = StatsFragment().apply {
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
        return inflater.inflate(R.layout.fragment_stats, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = checkNotNull(arguments?.getString("id"))
        dashboardViewModel.getPokemonById(id).observe(viewLifecycleOwner, Observer { pokemonValue ->
            pokemonValue?.let { pokemon ->
                textViewTypeDefenses.text = pokemon.ydescription

                textViewHP.text = pokemon.hp.toString()
                textViewAttack.text = pokemon.attack.toString()
                textViewDefense.text = pokemon.defense.toString()
                textViewSpAtk.text = pokemon.special_attack.toString()
                textViewSpDef.text = pokemon.special_defense.toString()
                textViewSpeed.text = pokemon.speed.toString()
                textViewTotal.text = pokemon.total.toString()

                progressBarHP.progress = pokemon.hp ?: 0
                progressBarAttack.progress = pokemon.attack ?: 0
                progressBarDefense.progress = pokemon.defense ?: 0
                progressBarSpAtk.progress = pokemon.special_attack ?: 0
                progressBarSpDef.progress = pokemon.special_defense ?: 0
                progressBarSpeed.progress = pokemon.speed ?: 0
                progressBarTotal.progress = pokemon.total ?: 0
            }
        })
    }
}
