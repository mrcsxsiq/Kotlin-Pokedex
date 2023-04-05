package dev.marcosfarias.pokedex.ui.dashboard

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import dev.marcosfarias.pokedex.R
import dev.marcosfarias.pokedex.ui.dashboard.about.AboutFragment
import dev.marcosfarias.pokedex.ui.dashboard.evolution.EvolutionFragment
import dev.marcosfarias.pokedex.ui.dashboard.moves.MovesFragment
import dev.marcosfarias.pokedex.ui.dashboard.stats.StatsFragment

class ViewPagerAdapter(
    supportFragmentManager: FragmentManager,
    context: Context,
    lifecycle: Lifecycle,
    private val pokemonId: String
) : FragmentStateAdapter(supportFragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return pages.size
    }

    override fun createFragment(position: Int): Fragment {
        return pages[position].ctor()
    }

    data class Page(val title: String, val ctor: () -> Fragment)

    @Suppress("MoveLambdaOutsideParentheses")
    private val pages = listOf(
        Page(
            context.getString(R.string.dashboard_tab_1),
            { AboutFragment.newInstance(pokemonId) }
        ),
        Page(
            context.getString(R.string.dashboard_tab_2),
            { StatsFragment.newInstance(pokemonId) }
        ),
        Page(
            context.getString(R.string.dashboard_tab_3),
            { EvolutionFragment.newInstance(pokemonId) }
        ),
        Page(
            context.getString(R.string.dashboard_tab_4),
            { MovesFragment() }
        )
    )

    fun getPageTitle(position: Int): CharSequence {
        return pages[position].title
    }
}
