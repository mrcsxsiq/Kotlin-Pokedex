package dev.marcosfarias.pokedex.ui.dashboard.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import dev.marcosfarias.pokedex.R
import dev.marcosfarias.pokedex.databinding.FragmentAboutBinding
import dev.marcosfarias.pokedex.ui.dashboard.DashboardViewModel
import dev.marcosfarias.pokedex.ui.dashboard.DashboardViewModelFactory

class AboutFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var binding: FragmentAboutBinding

    companion object {
        @JvmStatic
        fun newInstance(id: String) = AboutFragment().apply {
            arguments = AboutFragmentArgs.Builder(id).build().toBundle()
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val id = AboutFragmentArgs.fromBundle(arguments!!).pokemonId
        dashboardViewModel = ViewModelProviders.of(this, DashboardViewModelFactory(id)).get(DashboardViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_about, container, false)

        binding.vm = dashboardViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }
}