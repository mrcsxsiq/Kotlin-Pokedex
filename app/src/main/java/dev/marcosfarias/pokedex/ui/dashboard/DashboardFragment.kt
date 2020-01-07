package dev.marcosfarias.pokedex.ui.dashboard

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import dev.marcosfarias.pokedex.R
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

                    pokemon?.typeofpokemon?.getOrNull(0).let { firstType ->
                        textViewType3.text = firstType
                        textViewType3.isVisible = firstType != null
                    }

                    pokemon?.typeofpokemon?.getOrNull(1).let { secondType ->
                        textViewType2.text = secondType
                        textViewType2.isVisible = secondType != null
                    }

                    pokemon?.typeofpokemon?.getOrNull(2).let { thirdType ->
                        textViewType1.text = thirdType
                        textViewType1.isVisible = thirdType != null
                    }

                    Glide.with(view.context)
                        .load(pokemon?.imageurl)
                        .placeholder(android.R.color.transparent)
                        .into(imageView)

                    val pager = viewPager
                    val tabs = tabs
                    pager.adapter = ViewPagerAdapter(fragmentManager!!, context!!, pokemon?.id!!)
                    tabs.setupWithViewPager(pager)
                }


            })


        }
    }

}