package dev.marcosfarias.pokedex

import android.content.res.Resources
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import dev.marcosfarias.pokedex.robots.BaseRobot
import dev.marcosfarias.pokedex.ui.home.HomeFragment
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeTest : BaseRobot() {
    lateinit var navHost: TestNavHostController

    private val resources: Resources by lazy {
        InstrumentationRegistry.getInstrumentation().targetContext.resources
    }

    @Before
    fun setup() {
        // Create a TestNavHostController
        navHost = TestNavHostController(ApplicationProvider.getApplicationContext())
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
    fun testingNavigation() {
        assertEquals(navHost.graph.id, R.id.mobile_navigation)
    }

    @Test
    fun verifyIfRecyclerViewMenuIsDisplayed() {
        isViewDisplayed(R.id.recyclerViewMenu)
    }

    @Test
    fun verifyIfRecyclerViewTextIsDisplayed() {
        isRecyclerViewItemDisplayed(
            R.id.recyclerViewMenu,
            resources.getString(R.string.menu_item_1))
    }

    @Test
    fun verifyIfRecyclerViewNewsIsDisplayed() {
        isViewDisplayed(R.id.recyclerViewNews)
    }

    @Test
    fun verifyIfRecyclerViewItemIsDisplayed() {
        onClickItem(R.id.recyclerViewNews)
    }

    @Test
    fun testingTitleAndSearchBarText() {
        testViewText(
            R.id.search_title,
            resources.getString(R.string.main_title)
        )

        testViewText(
            R.id.search_text,
            resources.getString(R.string.main_search)
        )
    }
}
