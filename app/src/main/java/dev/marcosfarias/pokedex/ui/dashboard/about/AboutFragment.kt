package dev.marcosfarias.pokedex.ui.dashboard.about

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import dev.marcosfarias.pokedex.R
import dev.marcosfarias.pokedex.ui.dashboard.DashboardViewModel
import dev.marcosfarias.pokedex.utils.BundleKeyUtil.ID
import dev.marcosfarias.pokedex.utils.Converters
import dev.marcosfarias.pokedex.utils.PokemonStringUtil
import dev.marcosfarias.pokedex.utils.PokemonStringUtil.Companion.ENGLISH
import kotlinx.android.synthetic.main.fragment_about.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AboutFragment private constructor() : Fragment(R.layout.fragment_about) {

    private val dashboardViewModel: DashboardViewModel by viewModel()

    constructor(id: Int) : this() {
        arguments = Bundle().apply {
            putInt(ID, id)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = checkNotNull(arguments?.getInt(ID))

        dashboardViewModel.getPokemonById(id).observe(viewLifecycleOwner, Observer { pokemonValue ->

            pokemonValue?.let { pokemon ->
                descriptionField.text = pokemon.species.flavorTextEntries.find {
                    it.language.name == ENGLISH
                }?.flavorText?.replace("\n", " ")

                pokemon.height?.let { height ->
                    heightLabel.text =
                        PokemonStringUtil().formatPounds(Converters().fromCentimetersToFeet(height))
                }

                pokemon.weight?.let { weight ->
                    weightLabel.text =
                        PokemonStringUtil().formatFeet(Converters().fromKilogramsToPounds(weight))
                }

                genderField.text =
                    PokemonStringUtil().formatGenterRate(pokemon.species.genderRate)

                eggCycleField.text =
                    pokemon.species.hatchCounter.toString()

                eggGroupsField.text =
                    pokemon.species.eggGroups.map { it.name.capitalize() }.toString()
                        .removePrefix("[").removeSuffix("]")

                baseExperienceField.text = pokemon.baseExperience.toString()
            }
        })
    }
}
