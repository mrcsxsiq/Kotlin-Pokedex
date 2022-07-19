package dev.marcosfarias.pokedex.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import dev.marcosfarias.pokedex.R
import dev.marcosfarias.pokedex.databinding.FragmentHomeBinding
import dev.marcosfarias.pokedex.model.Menu
import dev.marcosfarias.pokedex.model.News
import dev.marcosfarias.pokedex.utils.PokemonColorUtil
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()
    private var viewBinding: FragmentHomeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.window?.statusBarColor = PokemonColorUtil(view.context).convertColor(R.color.red)

        viewBinding = FragmentHomeBinding.bind(view)

        viewBinding?.recyclerViewMenu?.layoutManager = GridLayoutManager(context, 2)

        viewBinding?.recyclerViewNews?.layoutManager = LinearLayoutManager(context)

        viewBinding?.recyclerViewNews?.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )

        homeViewModel.getListMenu().observe(viewLifecycleOwner, Observer {
            val items: List<Menu> = it
            viewBinding?.recyclerViewMenu?.adapter = MenuAdapter(items, view.context)
        })

        homeViewModel.getListNews().observe(viewLifecycleOwner, Observer {
            val items: List<News> = it
            viewBinding?.recyclerViewNews?.adapter = NewsAdapter(items, view.context)
        })
    }

    override fun onDestroyView() {
        viewBinding = null
        super.onDestroyView()
    }

}
