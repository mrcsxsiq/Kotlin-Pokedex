package dev.marcosfarias.pokedex.ui.dashboard.stats

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import dev.marcosfarias.pokedex.R
import dev.marcosfarias.pokedex.ui.dashboard.DashboardViewModel
import dev.marcosfarias.pokedex.utils.BundleKeyUtil.ID
import dev.marcosfarias.pokedex.utils.PokemonStringUtil.Companion.ATTACK
import dev.marcosfarias.pokedex.utils.PokemonStringUtil.Companion.DEFENSE
import dev.marcosfarias.pokedex.utils.PokemonStringUtil.Companion.ENGLISH
import dev.marcosfarias.pokedex.utils.PokemonStringUtil.Companion.HP
import dev.marcosfarias.pokedex.utils.PokemonStringUtil.Companion.SPECIAL_ATTACK
import dev.marcosfarias.pokedex.utils.PokemonStringUtil.Companion.SPECIAL_DEFENSE
import dev.marcosfarias.pokedex.utils.PokemonStringUtil.Companion.SPEED
import kotlinx.android.synthetic.main.fragment_stats.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class StatsFragment private constructor() : Fragment(R.layout.fragment_stats) {

    constructor(id: Int) : this() {
        arguments = Bundle().apply {
            putInt("id", id)
        }
    }

    private val dashboardViewModel: DashboardViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = checkNotNull(arguments?.getInt(ID))

        dashboardViewModel.getPokemon(id).observe(viewLifecycleOwner, Observer { pokemonValue ->
            pokemonValue?.let { pokemon ->

                hpField.text = pokemon.stats.find { it.stat.name == HP }?.baseStat.toString()
                attackField.text = pokemon.stats.find { it.stat.name == ATTACK }?.baseStat.toString()
                defenseField.text = pokemon.stats.find { it.stat.name == DEFENSE }?.baseStat.toString()
                specialAttackField.text = pokemon.stats.find { it.stat.name == SPECIAL_ATTACK }?.baseStat.toString()
                specialDefenseField.text = pokemon.stats.find { it.stat.name == SPECIAL_DEFENSE}?.baseStat.toString()
                speedField.text = pokemon.stats.find { it.stat.name == SPEED }?.baseStat.toString()
                totalField.text = pokemon.stats.map { it.baseStat }.sumBy { it }.toString()

                pokemon.stats.find { it.stat.name == HP }?.baseStat.let { hp ->
                    hpBar.progress = hp ?: 0
                }
                pokemon.stats.find { it.stat.name == ATTACK }?.baseStat.let { attack ->
                    attackBar.progress = attack ?: 0
                }
                pokemon.stats.find { it.stat.name == DEFENSE }?.baseStat.let { defense ->
                    defenseBar.progress = defense ?: 0
                }
                pokemon.stats.find { it.stat.name == SPECIAL_ATTACK }?.baseStat.let { specialAttack ->
                    specialAttackBar.progress = specialAttack ?: 0
                }
                pokemon.stats.find { it.stat.name == SPECIAL_DEFENSE }?.baseStat.let { specialDefense ->
                    specialDefenseBar.progress = specialDefense ?: 0
                }
                pokemon.stats.find { it.stat.name == SPEED }?.baseStat.let { speed ->
                    speedBar.progress = speed ?: 0
                }
                totalBar.progress = pokemon.stats.map { it.baseStat }.sumBy { it }
            }
        })

        dashboardViewModel.getSpecies(id).observe(viewLifecycleOwner, Observer { speciesValue ->
            speciesValue?.let { species ->
                typeDefensesField.text = species.flavorTextEntries.find {
                    it.language.name == ENGLISH
                }?.flavorText?.replace("\n", " ")
            }
        })
    }
}
