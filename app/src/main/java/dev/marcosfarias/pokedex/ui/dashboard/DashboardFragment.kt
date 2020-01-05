package dev.marcosfarias.pokedex.ui.dashboard

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import dev.marcosfarias.pokedex.R
import dev.marcosfarias.pokedex.adapter.ViewPagerAdapter
import dev.marcosfarias.pokedex.databinding.FragmentDashboardBinding
import dev.marcosfarias.pokedex.ui.dashboard.about.AboutFragment
import dev.marcosfarias.pokedex.ui.dashboard.evolution.EvolutionFragment
import dev.marcosfarias.pokedex.ui.dashboard.moves.MovesFragment
import dev.marcosfarias.pokedex.ui.dashboard.stats.StatsFragment
import dev.marcosfarias.pokedex.utils.PokemonColorUtil

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var binding: FragmentDashboardBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val id = DashboardFragmentArgs.fromBundle(arguments!!).pokemonId

        dashboardViewModel = ViewModelProviders.of(this, DashboardViewModelFactory(id)).get(DashboardViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, container, false)
        binding.vm = dashboardViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        dashboardViewModel.pokemon.observe(this, Observer { pokemon ->
            val color = PokemonColorUtil(requireContext()).getPokemonColor(pokemon?.typeofpokemon)
            val colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)

            binding.appBar.background.colorFilter = colorFilter
            binding.toolbarLayout.contentScrim?.colorFilter = colorFilter
            activity?.window?.statusBarColor = color

            pokemon?.typeofpokemon?.elementAtOrNull(0).let {
                binding.textViewType3.text = it
                if (it == null) {
                    binding.textViewType3.visibility = View.GONE
                }
            }

            pokemon?.typeofpokemon?.elementAtOrNull(1).let {
                binding.textViewType2.text = it
                if (it == null) {
                    binding.textViewType2.visibility = View.GONE
                }
            }

            pokemon?.typeofpokemon?.elementAtOrNull(2).let {
                binding.textViewType1.text = it
                if (it == null) {
                    binding.textViewType1.visibility = View.GONE
                }
            }

            Glide.with(requireContext())
                .load(pokemon?.imageurl)
                .placeholder(android.R.color.transparent)
                .into(binding.imageView)

            val pager = binding.viewPager
            val tabs = binding.tabs

            pager.adapter = ViewPagerAdapter(fragmentManager!!).apply {
                addFragment(AboutFragment.newInstance(id), getString(R.string.dashboard_tab_1))
                addFragment(StatsFragment.newInstance(id), getString(R.string.dashboard_tab_2))
                addFragment(EvolutionFragment(), getString(R.string.dashboard_tab_3))
                addFragment(MovesFragment(), getString(R.string.dashboard_tab_4))
            }

            tabs.setupWithViewPager(pager)
        })

        return binding.root
    }
}