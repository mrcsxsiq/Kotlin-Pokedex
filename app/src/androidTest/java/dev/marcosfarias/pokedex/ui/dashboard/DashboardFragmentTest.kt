package dev.marcosfarias.pokedex.ui.dashboard

import android.content.Context
import android.os.Bundle
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.marcosfarias.pokedex.R
import dev.marcosfarias.pokedex.database.AppDatabase
import dev.marcosfarias.pokedex.model.Pokemon
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.Executors

@RunWith(AndroidJUnit4::class)
class DashboardFragmentTest {
    lateinit var navController: TestNavHostController
    lateinit var bundle: Bundle

    val pokemon = Pokemon().apply {
        id = "#001"
        name = "Bulbasaur"
    }

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {

        bundle = bundleOf("id" to "#001")

        // Create a TestNavHostController
        navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )

        // Create a graphical FragmentScenario for the TitleScreen
        launchFragmentInContainer(themeResId = R.style.AppTheme, fragmentArgs = bundle) {
            DashboardFragment().also { fragment ->

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
    fun checkPokemonInfo() {
        Espresso.onView(
            ViewMatchers.withId(R.id.textViewName)
        ).check(ViewAssertions.matches(ViewMatchers.withText("Bulbasaur")))
    }

}