package dev.marcosfarias.pokedex.robots

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText

open class BaseRobot {

    fun testViewText(id: Int, text: String): ViewInteraction =
        onView(withId(id))
            .check(matches(withText(text)))

    fun onClick(id: Int): ViewInteraction =
        onView(withId(id))
            .perform(click())

    fun onClickItem(recyclerViewId: Int): ViewInteraction =
        onView(withId(recyclerViewId))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )

    fun isViewDisplayed(viewId: Int): ViewInteraction =
        onView(withId(viewId))
            .check(matches(isDisplayed()))

    fun isRecyclerViewItemDisplayed(
        viewId: Int,
        text: String
    ): ViewInteraction =
        onView(withId(viewId))
            .perform(
                RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
                    hasDescendant(withText(text)),
                    click()
                )
            )
}