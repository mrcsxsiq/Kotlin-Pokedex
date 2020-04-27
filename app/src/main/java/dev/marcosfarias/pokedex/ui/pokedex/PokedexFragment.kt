package dev.marcosfarias.pokedex.ui.pokedex

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.leinardi.android.speeddial.SpeedDialView
import dev.marcosfarias.pokedex.R
import dev.marcosfarias.pokedex.ui.generation.GenerationFragment
import dev.marcosfarias.pokedex.ui.search.SearchFragment
import dev.marcosfarias.pokedex.utils.PokemonColorUtil
import kotlinx.android.synthetic.main.fragment_pokedex.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PokedexFragment : Fragment(R.layout.fragment_pokedex) {

    private val pokedexViewModel: PokedexViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.window?.statusBarColor =
            PokemonColorUtil(view.context).convertColor(R.color.background)

        val spanCount = 2
        val initialOffSet = 0
        val listOffSet = 20

        pokedexLoadingProgressBar.visibility = View.VISIBLE
        val layoutManager = GridLayoutManager(context, spanCount)
        pokedexListRecyclerView.layoutManager = layoutManager
        val pokedexAdapter = PokedexAdapter()
        pokedexListRecyclerView.adapter = pokedexAdapter

        pokedexViewModel.getPokedexListIsLoading().observe(viewLifecycleOwner, Observer { loading ->
            if (loading) pokedexLoadingProgressBar.visibility = View.VISIBLE else pokedexLoadingProgressBar.visibility = View.GONE
        })

        pokedexViewModel.getPokedexList(initialOffSet.toString(), listOffSet.toString()).observe(viewLifecycleOwner, Observer { pokedexList ->
            pokedexAdapter.updatePokedexListData(pokedexList)

            if (pokedexList.isNotEmpty())
                pokedexLoadingProgressBar.visibility = View.GONE
        })

        pokedexListRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, horizontalScroll: Int, verticalScroll: Int) {
                val downDirection = 0
                if (verticalScroll > downDirection) {
                    val visiblePokemonCount = layoutManager.childCount
                    val totalPokemonCount = layoutManager.itemCount
                    val pastPokemonVisiblesItems = layoutManager.findFirstVisibleItemPosition()

                    if ((visiblePokemonCount + pastPokemonVisiblesItems) >= totalPokemonCount) {
                        pokedexViewModel.getPokedexList(totalPokemonCount.toString(), listOffSet.toString())
                    }
                }
            }
        })

        pokedexSpeedDialMenu.inflate(R.menu.menu_pokedex)
        pokedexSpeedDialMenu.setOnActionSelectedListener(SpeedDialView.OnActionSelectedListener { actionItem ->
            when (actionItem.id) {
                R.id.menuAllGen -> {
                    showAllGen()
                    pokedexSpeedDialMenu.close()
                    return@OnActionSelectedListener true
                }
                R.id.menuSearch -> {
                    showSearch()
                    pokedexSpeedDialMenu.close()
                    return@OnActionSelectedListener true
                }
                else -> {
                    pokedexSpeedDialMenu.close()
                    return@OnActionSelectedListener true
                }
            }
        })
    }

    private fun showAllGen() {
        val dialog = GenerationFragment()
        dialog.show(parentFragmentManager, "")
    }

    private fun showSearch() {
        val dialog = SearchFragment()
        dialog.show(parentFragmentManager, "")
    }
}
