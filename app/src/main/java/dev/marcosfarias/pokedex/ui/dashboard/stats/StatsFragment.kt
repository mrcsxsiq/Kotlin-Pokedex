package dev.marcosfarias.pokedex.ui.dashboard.stats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import dev.marcosfarias.pokedex.R
import dev.marcosfarias.pokedex.databinding.FragmentStatsBinding
import dev.marcosfarias.pokedex.ui.dashboard.DashboardViewModel
import dev.marcosfarias.pokedex.ui.dashboard.DashboardViewModelFactory

class StatsFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var binding: FragmentStatsBinding

    companion object {
        @JvmStatic
        fun newInstance(id: String) = StatsFragment().apply {
            arguments = StatsFragmentArgs.Builder(id).build().toBundle()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val id = StatsFragmentArgs.fromBundle(arguments!!).pokemonId
        dashboardViewModel = ViewModelProviders.of(this, DashboardViewModelFactory(id)).get(DashboardViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_stats, container, false)

        binding.vm = dashboardViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }
}