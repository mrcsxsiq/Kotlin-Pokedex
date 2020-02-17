package dev.marcosfarias.pokedex.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import dev.marcosfarias.pokedex.R
import dev.marcosfarias.pokedex.model.Menu
import dev.marcosfarias.pokedex.model.News
import dev.marcosfarias.pokedex.utils.PokemonColorUtil
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val homeViewModel: HomeViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.window?.statusBarColor = PokemonColorUtil(view.context).convertColor(R.color.red)

        val recyclerViewMenu = recyclerViewMenu
        val recyclerViewNews = recyclerViewNews

        recyclerViewMenu.layoutManager = GridLayoutManager(context, 2)

        recyclerViewNews.layoutManager = GridLayoutManager(context, 1)
        recyclerViewNews.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )

        homeViewModel.getListMenu().observe(viewLifecycleOwner, Observer {
            val items: List<Menu> = it
            recyclerViewMenu.adapter = MenuAdapter(items, view.context)
        })

        homeViewModel.getListNews().observe(viewLifecycleOwner, Observer {
            val items: List<News> = it
            recyclerViewNews.adapter = NewsAdapter(items, view.context)
        })
    }
}
