package dev.marcosfarias.pokedex.ui.dashboard

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import dev.marcosfarias.pokedex.R
import dev.marcosfarias.pokedex.ui.dashboard.about.AboutFragment
import dev.marcosfarias.pokedex.ui.dashboard.evolution.EvolutionFragment
import dev.marcosfarias.pokedex.ui.dashboard.moves.MovesFragment
import dev.marcosfarias.pokedex.ui.dashboard.stats.StatsFragment
import dev.marcosfarias.pokedex.utils.PokemonColorUtil
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString("id").let {
            dashboardViewModel.getPokemonById(it).observe(viewLifecycleOwner, Observer { list ->
                list?.get(0).let { pokemon ->
                    textViewID.text = pokemon?.id
                    textViewName.text = pokemon?.name

                    val color =
                        PokemonColorUtil(view.context).getPokemonColor(pokemon?.typeofpokemon)
                    app_bar.background.colorFilter =
                        PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)
                    toolbar_layout.contentScrim?.colorFilter =
                        PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)
                    activity?.window?.statusBarColor =
                        PokemonColorUtil(view.context).getPokemonColor(pokemon?.typeofpokemon)

                    pokemon?.typeofpokemon?.elementAtOrNull(0).let {
                        textViewType3.text = it
                        if (it == null) {
                            textViewType3.visibility = View.GONE
                        }
                    }

                    pokemon?.typeofpokemon?.elementAtOrNull(1).let {
                        textViewType2.text = it
                        if (it == null) {
                            textViewType2.visibility = View.GONE
                        }
                    }

                    pokemon?.typeofpokemon?.elementAtOrNull(2).let {
                        textViewType1.text = it
                        if (it == null) {
                            textViewType1.visibility = View.GONE
                        }
                    }

                    Glide.with(view.context)
                        .load(pokemon?.imageurl)
                        .placeholder(android.R.color.transparent)
                        .into(imageView)

                    val pager = viewPager
                    val tabs = tabs


                    val adapter = ViewPagerAdapter(fragmentManager!!)
                    adapter.addFragment(
                        AboutFragment.newInstance(pokemon?.id),
                        getString(R.string.dashboard_tab_1)
                    )
                    adapter.addFragment(
                        StatsFragment.newInstance(pokemon?.id),
                        getString(R.string.dashboard_tab_2)
                    )
                    adapter.addFragment(EvolutionFragment(), getString(R.string.dashboard_tab_3))
                    adapter.addFragment(MovesFragment(), getString(R.string.dashboard_tab_4))

                    pager.adapter = adapter

                    tabs.setupWithViewPager(pager)
                }


            })


        }
    }

}