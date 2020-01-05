package dev.marcosfarias.pokedex.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import dev.marcosfarias.pokedex.App
import dev.marcosfarias.pokedex.R
import dev.marcosfarias.pokedex.adapter.MenuAdapter
import dev.marcosfarias.pokedex.adapter.NewsAdapter
import dev.marcosfarias.pokedex.model.Menu
import dev.marcosfarias.pokedex.model.News
import dev.marcosfarias.pokedex.utils.PokemonColorUtil
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_home, container, false)
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        activity?.window?.statusBarColor = PokemonColorUtil(root.context).covertColor(R.color.red)

        val recyclerViewMenu = root.recyclerViewMenu
        val recyclerViewNews = root.recyclerViewNews

        recyclerViewMenu.layoutManager = GridLayoutManager(context, 2)

        recyclerViewNews.layoutManager = LinearLayoutManager(context)
        recyclerViewNews.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        homeViewModel.getListMenu().observe(this, Observer {
            recyclerViewMenu.adapter = MenuAdapter(it, root.context)
        })

        homeViewModel.getListNews().observe(this, Observer {
            recyclerViewNews.adapter = NewsAdapter(it, root.context)
        })
        return root
    }



}