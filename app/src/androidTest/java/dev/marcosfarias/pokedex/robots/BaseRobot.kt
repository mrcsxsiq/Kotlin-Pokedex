package dev.marcosfarias.pokedex.robots

import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers

class BaseRobot {

    fun testViewText(id: Int, text: String) : ViewInteraction =
         Espresso.onView(ViewMatchers.withId(id))
            .check(ViewAssertions.matches(ViewMatchers.withText(text)))
}