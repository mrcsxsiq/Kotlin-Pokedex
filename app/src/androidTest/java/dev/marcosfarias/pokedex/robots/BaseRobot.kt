package dev.marcosfarias.pokedex.robots

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import dev.marcosfarias.pokedex.R

open class BaseRobot {

    fun testViewText(id: Int, text: String): ViewInteraction =
        onView(withId(id))
            .check(ViewAssertions.matches(withText(text)))

    // Click for View with type Button
    fun onClick(id: Int): ViewInteraction = onView(
        withId(id)
    ).perform(
        click()
    )

    // Click for RecyclerView
    fun onClickItem(recyclerViewId: Int): ViewInteraction = onView(
        withId(recyclerViewId)
    ).perform(
        RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
    )

    fun isViewDisplayed(viewId: Int): ViewInteraction =
        onView(withId(viewId)).check(ViewAssertions.matches(isDisplayed()))

    fun isRecyclerViewItemDisplayed(viewId: Int, text: String): ViewInteraction =
        onView(withId(viewId)).perform(
            RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
                hasDescendant(
                    withText(text)
                ),
                click()
            )
        )
}