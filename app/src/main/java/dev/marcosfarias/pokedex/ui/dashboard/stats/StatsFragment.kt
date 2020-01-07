package dev.marcosfarias.pokedex.ui.dashboard.stats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import dev.marcosfarias.pokedex.R
import dev.marcosfarias.pokedex.ui.dashboard.DashboardViewModel
import kotlinx.android.synthetic.main.fragment_stats.view.*

class StatsFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance(id: String?) = StatsFragment().apply {
            arguments = Bundle().apply {
                putString("id", id)
            }
        }
    }

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel = ViewModelProviders.of(this).get(DashboardViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_stats, container, false)

        arguments?.getString("id").let {

            dashboardViewModel.getPokemonById(it).observe(this, Observer { list ->
                list?.get(0).let { pokemon ->

                    root.textViewTypeDefenses.text = pokemon?.ydescription

                    root.textViewHP.text = pokemon?.hp.toString()
                    root.textViewAttack.text = pokemon?.attack.toString()
                    root.textViewDefense.text = pokemon?.defense.toString()
                    root.textViewSpAtk.text = pokemon?.special_attack.toString()
                    root.textViewSpDef.text = pokemon?.special_defense.toString()
                    root.textViewSpeed.text = pokemon?.speed.toString()
                    root.textViewTotal.text = pokemon?.total.toString()

                    root.progressBarHP.progress = pokemon?.hp ?: 0
                    root.progressBarAttack.progress = pokemon?.attack ?: 0
                    root.progressBarDefense.progress = pokemon?.defense ?: 0
                    root.progressBarSpAtk.progress = pokemon?.special_attack ?: 0
                    root.progressBarSpDef.progress = pokemon?.special_defense ?: 0
                    root.progressBarSpeed.progress = pokemon?.speed ?: 0
                    root.progressBarTotal.progress = pokemon?.total ?: 0
                }
            })
        }

        return root
    }


}