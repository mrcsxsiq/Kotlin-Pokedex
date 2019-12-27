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
import dev.marcosfarias.pokedex.utils.PokemonColorUtil
import kotlinx.android.synthetic.main.fragment_dashboard.view.*
import kotlinx.android.synthetic.main.fragment_dashboard.view.imageView
import kotlinx.android.synthetic.main.fragment_dashboard.view.textViewID
import kotlinx.android.synthetic.main.fragment_dashboard.view.textViewName
import kotlinx.android.synthetic.main.fragment_dashboard.view.textViewType1
import kotlinx.android.synthetic.main.fragment_dashboard.view.textViewType2
import kotlinx.android.synthetic.main.fragment_dashboard.view.textViewType3
import kotlinx.android.synthetic.main.item_pokemon.view.*

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ) : View? {
        dashboardViewModel = ViewModelProviders.of(this).get(DashboardViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)

        arguments?.getString("id").let {

            dashboardViewModel.getPokemonById(it).observe(this, Observer { list ->
                list?.get(0).let { pokemon ->
                    root.textViewID.text = pokemon?.id
                    root.textViewName.text = pokemon?.name

                    val color = PokemonColorUtil(root.context).getPokemonColor(pokemon?.typeofpokemon)
                    root.app_bar.background.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP )
                    root.toolbar_layout.contentScrim?.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP )
                    activity?.window?.statusBarColor = PokemonColorUtil(root.context).getPokemonColor(pokemon?.typeofpokemon)

                    pokemon?.typeofpokemon?.elementAtOrNull(0).let {
                        root.textViewType3.text = it
                        if (it == null) {
                            root.textViewType3.visibility = View.GONE
                        }
                    }

                    pokemon?.typeofpokemon?.elementAtOrNull(1).let {
                        root.textViewType2.text = it
                        if (it == null) {
                            root.textViewType2.visibility = View.GONE
                        }
                    }

                    pokemon?.typeofpokemon?.elementAtOrNull(2).let {
                        root.textViewType1.text = it
                        if (it == null) {
                            root.textViewType1.visibility = View.GONE
                        }
                    }

                    Glide.with(root.context)
                        .load(pokemon?.imageurl)
                        .placeholder(android.R.color.transparent)
                        .into(root.imageView)

                }


            })


        }


        return root
    }




}