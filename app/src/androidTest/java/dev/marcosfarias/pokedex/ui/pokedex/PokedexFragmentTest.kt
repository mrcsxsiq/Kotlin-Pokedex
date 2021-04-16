package dev.marcosfarias.pokedex.ui.pokedex

import android.util.Log
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.marcosfarias.pokedex.R
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class PokedexFragmentTest {

    lateinit var navController: TestNavHostController

    @Before
    fun setup() {
        // Create a TestNavHostController
        navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )

        // Create a graphical FragmentScenario for the TitleScreen
        launchFragmentInContainer(themeResId = R.style.AppTheme) {
            PokedexFragment().also { fragment ->

                // In addition to returning a new instance of our Fragment,
                // get a callback whenever the fragment’s view is created
                // or destroyed so that we can set the NavController
                fragment.viewLifecycleOwnerLiveData.observeForever { viewLifecycleOwner ->
                    if (viewLifecycleOwner != null) {
                        // The fragment’s view has just been created
                        navController.setGraph(R.navigation.mobile_navigation)
                        Navigation.setViewNavController(fragment.requireView(), navController)
                    }
                }
            }
        }
    }

    @Test
    fun checkIfOpenPokemonDetails() {

        Log.d("Heigon",R.id.navigation_pokedex.toString())
        Log.d("Heigon",navController.currentDestination?.id.toString())

        Espresso.onView(ViewMatchers.withId(R.id.recyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )

        Log.d("Heigon",R.id.navigation_dashboard.toString())
        Log.d("Heigon",navController.currentDestination?.id.toString())

        assertEquals(R.id.navigation_dashboard,navController.currentDestination?.id)
    }
}