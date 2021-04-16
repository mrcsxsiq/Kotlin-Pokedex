package dev.marcosfarias.pokedex

import android.content.res.Resources
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import dev.marcosfarias.pokedex.robots.BaseRobot
import dev.marcosfarias.pokedex.ui.newsdetail.NewsDetailFragment
import org.junit.Before
import org.junit.Test

class FragmentNewsDetailsTest: BaseRobot() {

    lateinit var navHost: TestNavHostController

    private val resources: Resources by lazy {
        InstrumentationRegistry.getInstrumentation().targetContext.resources
    }

    @Before
    fun setup() {
        // Create a TestNavHostController
        navHost = TestNavHostController(ApplicationProvider.getApplicationContext())
        launchFragmentInContainer(themeResId = R.style.AppTheme) {
            NewsDetailFragment().also { fragment ->
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
    fun verifyIfTitleTextIsDisplayed() {
        isViewDisplayed(R.id.txtNewsTitle)
    }

}