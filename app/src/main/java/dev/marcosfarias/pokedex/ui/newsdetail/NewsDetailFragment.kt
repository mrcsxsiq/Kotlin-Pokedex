package dev.marcosfarias.pokedex.ui.newsdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import dev.marcosfarias.pokedex.R
import dev.marcosfarias.pokedex.utils.PokemonColorUtil
import kotlinx.android.synthetic.main.fragment_news_detail.view.*


class NewsDetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ) : View? {

        val root = inflater.inflate(R.layout.fragment_news_detail, container, false)

        activity?.window?.statusBarColor = PokemonColorUtil(root.context).covertColor(R.color.white)

        return root
    }

}