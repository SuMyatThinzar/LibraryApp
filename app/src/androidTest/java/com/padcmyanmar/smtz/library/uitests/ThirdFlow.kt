package com.padcmyanmar.smtz.library.uitests

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.padcmyanmar.smtz.library.R
import com.padcmyanmar.smtz.library.activities.MainScreenActivity
import com.padcmyanmar.smtz.library.views.viewholders.SearchBookViewHolder
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4ClassRunner::class)
class ThirdFlow {

    private val activityTestRule = ActivityTestRule<MainScreenActivity>(MainScreenActivity::class.java)

    @Before
    open fun setUp() {
        activityTestRule.launchActivity(Intent())
    }

    @Test
    fun test1SearchGoogleBook(){
        onView(withId(R.id.cardViewSearch)).perform(click())
        onView(withId(R.id.etSearch)).check(matches(isDisplayed()))
        onView(withId(R.id.etSearch)).perform(ViewActions.typeText(TEST_FLUTTER), closeSoftKeyboard())

        Thread.sleep(3000L)
//        onView(withText("Practical Flutter")).check(matches(isDisplayed()))
//        onView(withId(R.id.rvSearchBookList))
//            .check(matches(isDisplayed()))
//            .perform(RecyclerViewActions.scrollTo(withText("Text XYZ")),click())

        onView(withId(R.id.rvSearchBookList))
            .perform(RecyclerViewActions.scrollTo<SearchBookViewHolder>(
                hasDescendant(
                    withText("Beginning Flutter")
                )
            ), click()
        )

    }

    companion object {
        const val TEST_FLUTTER = "Flutter"
    }

}