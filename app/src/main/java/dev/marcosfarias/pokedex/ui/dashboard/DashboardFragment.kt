package dev.marcosfarias.pokedex.ui.dashboard

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import dev.marcosfarias.pokedex.R
import dev.marcosfarias.pokedex.utils.*
import kotlinx.android.synthetic.main.fragment_dashboard.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    private val dashboardViewModel: DashboardViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = checkNotNull(arguments?.getInt(BundleKeyUtil.ID))

        dashboardViewModel.getPokemonById(id).observe(viewLifecycleOwner, Observer { pokemonValue ->

            pokemonValue?.let { pokemon ->
                pokemonIDLabel.text = PokemonStringUtil().formatId(pokemon.id)

                pokemonNameLabel.text = pokemon.name.capitalize()

                val color =
                    PokemonColorUtil(view.context).getColor(pokemon.types.last().type.name)

                appBar.background = ColorDrawable(color)

                toolbarLayout.contentScrim?.colorFilter =
                    PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)

                activity?.window?.statusBarColor = color

                pokemon.types.getOrNull(TypeIndexEnum.THIRD.index)?.type?.let { thirdType ->
                    thirdTypeLabel.text = thirdType.name.capitalize()
                }
                pokemon.types.getOrNull(TypeIndexEnum.SECOND.index)?.type?.let { secondType ->
                    secondTypeLabel.text = secondType.name.capitalize()
                }
                pokemon.types.getOrNull(TypeIndexEnum.FIRST.index)?.type?.let { firstType ->
                    firstTypeLabel.text = firstType.name.capitalize()
                }

                firstTypeLabel.visibility =
                    if (firstTypeLabel.text.isNotEmpty()) View.VISIBLE else View.GONE

                secondTypeLabel.visibility =
                    if(secondTypeLabel.text.isNotEmpty() &&
                        secondTypeLabel.text != firstTypeLabel.text) View.VISIBLE else View.GONE

                thirdTypeLabel.visibility =
                    if(thirdTypeLabel.text.isNotEmpty() &&
                        thirdTypeLabel.text != secondTypeLabel.text) View.VISIBLE else View.GONE

                PokemonImageUtil().loadPokemonImage(view.context, id, imageView)

                dashboardViewPager.adapter =
                    DashboardViewPagerAdapter(parentFragmentManager, requireContext(), pokemon.id)

                dashboardTabs.setupWithViewPager(dashboardViewPager)
            }
        })
    }
}
