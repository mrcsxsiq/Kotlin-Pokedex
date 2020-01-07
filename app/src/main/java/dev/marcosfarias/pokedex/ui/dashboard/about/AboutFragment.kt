package dev.marcosfarias.pokedex.ui.dashboard.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import dev.marcosfarias.pokedex.R
import dev.marcosfarias.pokedex.ui.dashboard.DashboardViewModel
import kotlinx.android.synthetic.main.fragment_about.view.*

class AboutFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance(id: String?) = AboutFragment().apply {
            arguments = Bundle().apply {
                putString("id", id)
            }
        }
    }

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dashboardViewModel = ViewModelProviders.of(this).get(DashboardViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_about, container, false)

        arguments?.getString("id").let {

            dashboardViewModel.getPokemonById(it).observe(this, Observer { list ->
                list?.get(0).let { pokemon ->

                    root.textViewDescription.text = pokemon?.xdescription
                    root.textViewHeight.text = pokemon?.height
                    root.textViewWeight.text = pokemon?.weight
                    root.textViewEggCycle.text = pokemon?.cycles
                    root.textViewEggGroups.text = pokemon?.egg_groups
                    root.textViewBaseEXP.text = pokemon?.base_exp
                }
            })
        }


        return root
    }


}