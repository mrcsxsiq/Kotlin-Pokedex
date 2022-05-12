package dev.marcosfarias.pokedex.ui.dashboard

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import dev.marcosfarias.pokedex.GlideApp
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import dev.marcosfarias.pokedex.R
import dev.marcosfarias.pokedex.databinding.FragmentDashboardBinding
import dev.marcosfarias.pokedex.utils.ImageLoadingListener
import dev.marcosfarias.pokedex.utils.PokemonColorUtil
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardFragment : Fragment() {

    private val dashboardViewModel: DashboardViewModel by viewModel()
    private var dashboardViewBinding: FragmentDashboardBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(requireContext())
            .inflateTransition(R.transition.image_shared_element_transition)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        postponeEnterTransition()
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = checkNotNull(arguments?.getString("id"))
        val name = checkNotNull(arguments?.getString("name"))

        dashboardViewBinding = FragmentDashboardBinding.bind(view)

        dashboardViewBinding?.imageView?.transitionName = name

        dashboardViewModel.getPokemonById(id).observe(viewLifecycleOwner, Observer { pokemonValue ->
            pokemonValue?.let { pokemon ->
                dashboardViewBinding?.textViewID?.text = pokemon.id
                dashboardViewBinding?.textViewName?.text = pokemon.name

                val color = PokemonColorUtil(view.context).getPokemonColor(pokemon.typeofpokemon)
                dashboardViewBinding?.appBar?.setBackgroundColor(color)
                dashboardViewBinding?.toolbarLayout?.contentScrim?.colorFilter =
                    PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)
                activity?.window?.statusBarColor =
                    PokemonColorUtil(view.context).getPokemonColor(pokemon.typeofpokemon)

                pokemon.typeofpokemon?.getOrNull(0).let { firstType ->
                    dashboardViewBinding?.textViewType3?.text = firstType
                    dashboardViewBinding?.textViewType3?.isVisible = firstType != null
                }

                pokemon.typeofpokemon?.getOrNull(1).let { secondType ->
                    dashboardViewBinding?.textViewType2?.text = secondType
                    dashboardViewBinding?.textViewType2?.isVisible = secondType != null
                }

                pokemon.typeofpokemon?.getOrNull(2).let { thirdType ->
                    dashboardViewBinding?.textViewType1?.text = thirdType
                    dashboardViewBinding?.textViewType1?.isVisible = thirdType != null
                }

                dashboardViewBinding?.imageView?.let {
                    GlideApp.with(view.context)
                        .load(pokemon.imageurl)
                        .listener(ImageLoadingListener {
                            startPostponedEnterTransition()
                        })
                        .into(it)
                }
                val pager = dashboardViewBinding?.viewPager
                val tabs = dashboardViewBinding?.tabs
                pager?.adapter =
                    ViewPagerAdapter(childFragmentManager, requireContext(), pokemon.id)
                tabs?.setupWithViewPager(pager)
            }
        })
    }

    override fun onDestroyView() {
        dashboardViewBinding = null
        super.onDestroyView()
    }
}
