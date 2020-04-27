package dev.marcosfarias.pokedex.ui.dashboard

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import dev.marcosfarias.pokedex.R
import dev.marcosfarias.pokedex.utils.PokemonColorUtil
import dev.marcosfarias.pokedex.utils.PokemonStringUtil
import kotlinx.android.synthetic.main.fragment_dashboard.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    private val dashboardViewModel: DashboardViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = checkNotNull(arguments?.getString("id"))
        dashboardViewModel.getPokemonById(id).observe(viewLifecycleOwner, Observer { pokemonValue ->
            pokemonValue?.let { pokemon ->
                pokemonIDLabel.text = PokemonStringUtil().formatId(pokemon.id)
                pokemonNameLabel.text = pokemon.name.capitalize()

                val color =
                    PokemonColorUtil(view.context).getColor(pokemon.types.last().type.name)
                app_bar.background.colorFilter =
                    PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)
                toolbar_layout.contentScrim?.colorFilter =
                    PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)
                activity?.window?.statusBarColor =
                    PokemonColorUtil(view.context).getColor(pokemon.types.last().type.name)

                pokemon.types.getOrNull(0)?.type?.let { firstType ->
                    firstTypeLabel.text = firstType.name.capitalize()
                }
                firstTypeLabel.visibility = if (firstTypeLabel.text.isNotEmpty()) View.VISIBLE else View.GONE

                pokemon.types.getOrNull(1)?.type?.let { secondType ->
                    secondTypeLabel.text = secondType.name.capitalize()
                }
                secondTypeLabel.visibility = if (secondTypeLabel.text.isNotEmpty()) View.VISIBLE else View.GONE

                pokemon.types.getOrNull(2)?.type?.let { thirdType ->
                    thirdTypeLabel.text = thirdType.name.capitalize()
                }
                thirdTypeLabel.visibility = if (thirdTypeLabel.text.isNotEmpty()) View.VISIBLE else View.GONE

//                TODO: Fazer carregamento de Imagem
//                Glide.with(view.context)
//                    .load(pokemon.imageurl)
//                    .placeholder(android.R.color.transparent)
//                    .into(imageView)

                val pager = viewPager
                val tabs = tabs
                pager.adapter =
                    ViewPagerAdapter(parentFragmentManager, requireContext(), pokemon.id.toString())
                tabs.setupWithViewPager(pager)
            }
        })
    }
}
