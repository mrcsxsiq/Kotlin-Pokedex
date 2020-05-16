package dev.marcosfarias.pokedex.ui.dashboard.evolution

import androidx.fragment.app.Fragment
import dev.marcosfarias.pokedex.R
import dev.marcosfarias.pokedex.ui.dashboard.DashboardViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class EvolutionFragment : Fragment(R.layout.fragment_evolution) {

    private val dashboardViewModel: DashboardViewModel by viewModel()
}
