package dev.marcosfarias.pokedex

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.marcosfarias.pokedex.robots.BaseRobot
import dev.marcosfarias.pokedex.ui.home.HomeFragment
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeTest {
    lateinit var navHost: TestNavHostController
    lateinit var robot: BaseRobot


    @Before
    fun setup() { // Create a TestNavHostController
        robot = BaseRobot()
        navHost = TestNavHostController(ApplicationProvider.getApplicationContext())
        // Create a graphical FragmentScenario for the TitleScreen
        launchFragmentInContainer(themeResId = R.style.AppTheme) {
            HomeFragment().also { fragment ->
                // In addition to returning a new instance of our Fragment, // get a callback whenever the fragment’s view is created // or destroyed so that we can set the NavController
                fragment.viewLifecycleOwnerLiveData.observeForever { viewLifecycleOwner ->
                    if (viewLifecycleOwner != null) {
                        // The fragment’s view has just been created
                        navHost.setGraph(R.navigation.mobile_navigation)
                        Navigation.setViewNavController(fragment.requireView(), navHost)
                    }
                }
            }
        }
    }


    @Test
    fun testingNavigation(){
        //TODO
    }

    @Test
    fun testingTitleAndSearchBarText(){
        robot.testViewText(R.id.search_text, R.string.main_search.toString())
    }


}
