package dev.marcosfarias.pokedex.ui.newsdetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import dev.marcosfarias.pokedex.R
import dev.marcosfarias.pokedex.utils.PokemonColorUtil

class NewsDetailFragment : Fragment(R.layout.fragment_news_detail) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.window?.statusBarColor =
            PokemonColorUtil(view.context).convertColor(R.color.white)
    }
}
