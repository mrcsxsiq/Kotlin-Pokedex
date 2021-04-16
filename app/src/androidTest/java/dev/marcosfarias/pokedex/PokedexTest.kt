package dev.marcosfarias.pokedex

import android.content.res.Resources
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import dev.marcosfarias.pokedex.robots.BaseRobot
import dev.marcosfarias.pokedex.ui.pokedex.PokedexFragment
import org.junit.Before
import org.junit.Test

class PokedexTest : BaseRobot() {

    lateinit var navHost: TestNavHostController

    private val resources: Resources by lazy {
        InstrumentationRegistry.getInstrumentation().targetContext.resources
    }

    @Before
    fun setup() {
        // Create a TestNavHostController
        navHost = TestNavHostController(ApplicationProvider.getApplicationContext())
        launchFragmentInContainer(themeResId = R.style.AppTheme) {
            PokedexFragment().also { fragment ->
                // In addition to returning a new instance of our Fragment, // get a callback whenever the fragment’s view is created // or destroyed so that we can set the NavController
                fragment.viewLifecycleOwnerLiveData.observeForever { viewLifecycleOwner ->
                    if (viewLifecycleOwner != null) {
                        // The fragment’s view has just been created
                        navHost.setGraph(R.navigation.mobile_navigation)
                        navHost.setCurrentDestination(R.id.navigation_pokedex)
                        Navigation.setViewNavController(fragment.requireView(), navHost)
                    }
                }
            }
        }
    }

    @Test
    fun verifyIfRecyclerViewIsDisplayed() {
        isViewDisplayed(R.id.recyclerView)
    }

    @Test
    fun verifyIfPokemonDataisDisplayedOnCard() {
        onClickItem(R.id.recyclerView)
    }

    @Test
    fun verifyIfFabIsDisplayed() {
        isViewDisplayed(R.id.speedDial)
    }
    @Test
    fun verifyIfFabCanBeExtended(){
        onClick(R.id.speedDial)
    }
}