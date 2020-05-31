package dev.marcosfarias.pokedex.ui.dashboard

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import dev.marcosfarias.pokedex.R
import dev.marcosfarias.pokedex.ui.dashboard.about.AboutFragment
import dev.marcosfarias.pokedex.ui.dashboard.evolution.EvolutionFragment
import dev.marcosfarias.pokedex.ui.dashboard.moves.MovesFragment
import dev.marcosfarias.pokedex.ui.dashboard.stats.StatsFragment

class DashboardViewPagerAdapter(
    supportFragmentManager: FragmentManager,
    context: Context,
    private val pokemonId: Int
) : FragmentStatePagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    data class Page(val title: String, val content: () -> Fragment)

    @Suppress("MoveLambdaOutsideParentheses")
    private val pages = listOf(
        Page(
            context.getString(R.string.dashboard_first_page),
            { AboutFragment(pokemonId) }
        ),
        Page(
            context.getString(R.string.dashboard_second_tab),
            { StatsFragment(pokemonId) }
        ),
        Page(
            context.getString(R.string.dashboard_third_tab),
            { EvolutionFragment.newInstance(pokemonId) }
        ),
        Page(
            context.getString(R.string.dashboard_fourth_tab),
            { MovesFragment() }
        )
    )

    override fun getItem(position: Int): Fragment {
        return pages[position].content()
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return pages[position].title
    }
}
