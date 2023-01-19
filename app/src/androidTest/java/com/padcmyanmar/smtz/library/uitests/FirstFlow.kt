package com.padcmyanmar.smtz.library.uitests

import android.content.Intent
import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.padcmyanmar.smtz.library.activities.MainScreenActivity
import com.padcmyanmar.smtz.library.R
import com.padcmyanmar.smtz.library.uitests.utils.first
import com.padcmyanmar.smtz.library.views.viewholders.BookListViewHolder
import com.padcmyanmar.smtz.library.views.viewholders.GenreChipViewHolder
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4ClassRunner::class)
class FirstFlow {
    private val activityTestRule = ActivityTestRule<MainScreenActivity>(MainScreenActivity::class.java)

    @Before
    open fun setUp() {
        activityTestRule.launchActivity(Intent())
    }

    @Test
    fun test1CarouselViewEmptyTest(){

        onView(withId(R.id.vpEmptyCarousel))
            .check(matches(isDisplayed()))
    }

    @Test
    fun test2CaseCheckBookListName(){

        Thread.sleep(5000L)
        onView(withId(R.id.rvBookLists))
//            .inRoot(RootMatchers.withDecorView(Matchers.`is`(activityTestRule.activity.window.decorView)))
            .check(matches(isDisplayed()))

        onView(withText("Hardcover Fiction"))
            .check(matches(isDisplayed()))

        onView(first<View>(withText("THE CHOICE")))
            .check(matches(isDisplayed()))

        onView(first<View>(withText("FAIRY TALE")))
            .check(matches(isDisplayed()))

        onView(first<View>(withText("THE BOYS FROM BILOXI")))
            .check(matches(isDisplayed()))

    }
    
    @Test
    fun test3TapOnBookOne_navigateToBookDetail(){

        Thread.sleep(7000L)
        onView(withId(R.id.rvBookLists))
            .perform(RecyclerViewActions.actionOnItemAtPosition<BookListViewHolder>(0, click()))

//        onView(first<View>(withText("THE CHOICE")))
//            .perform(click())

        onView(withId(R.id.tvBookNameBookDetail))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tvBookAuthorNameBookDetail))
            .check(matches(isDisplayed()))
        onView(withId(R.id.ivBookImage))
            .check(matches(isDisplayed()))
    }

    @Test
    fun test4TapOnBookTwo_navigateToBookDetail(){

        Thread.sleep(7000L)
        onView(withId(R.id.rvBookLists))
            .perform(scrollTo())

        onView(withId(R.id.rvBookLists))
//            .perform(RecyclerViewActions.scrollToPosition<BookListViewHolder>(2))
            .perform(RecyclerViewActions.actionOnItemAtPosition<BookListViewHolder>(1, click()))

        onView(withId(R.id.tvBookNameBookDetail))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tvBookAuthorNameBookDetail))
            .check(matches(isDisplayed()))
        onView(withId(R.id.ivBookImage))
            .check(matches(isDisplayed()))
    }

    @Test
    fun test5TapOnBookThree_navigateToBookDetail(){

        Thread.sleep(10000L)
        onView(withId(R.id.rvBookLists))
            .perform(RecyclerViewActions.actionOnItemAtPosition<BookListViewHolder>(2, scrollTo()))

        onView(withId(R.id.rvBookLists))
            .perform(RecyclerViewActions.actionOnItemAtPosition<BookListViewHolder>(2, click()))

        onView(withId(R.id.tvBookNameBookDetail))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tvBookAuthorNameBookDetail))
            .check(matches(isDisplayed()))
        onView(withId(R.id.ivBookImage))
            .check(matches(isDisplayed()))
    }

    @Test
    fun test6CaseCheckCarouselBookList(){

        onView(withId(R.id.carouselRecyclerview))
            .check(matches(hasChildCount(3)))
    }

    @Test
    fun test7CaseCheckLibraryBookList(){

        onView(withId(R.id.action_library))
            .perform(click())
        onView(withId(R.id.rvLibraryBooks))
            .check(matches(hasChildCount(3)))
    }

    @Test
    fun test8CaseCheckViews(){

        onView(withId(R.id.action_library)).perform(click())

        onView(withId(R.id.btnView)).perform(click())
        onView(withId(R.id.radioButtonList)).perform(click())
        onView(withId(R.id.rvLibraryBooks)).check(matches(isDisplayed()))

        onView(withId(R.id.btnView)).perform(click())
        onView(withId(R.id.radioButtonLargeGrid)).perform(click())
        onView(withId(R.id.rvLibraryBooks)).check(matches(isDisplayed()))

        onView(withId(R.id.btnView)).perform(click())
        onView(withId(R.id.radioButtonSmallGrid)).perform(click())
        onView(withId(R.id.rvLibraryBooks)).check(matches(isDisplayed()))
    }

    @Test
    fun test9OnTapGenreList_checkBookList(){

        onView(withId(R.id.action_library)).perform(click())

        onView(withId(R.id.rvGenreChips)).perform(RecyclerViewActions.actionOnItemAtPosition<GenreChipViewHolder>(0, click()))
        onView(withId(R.id.rvLibraryBooks)).check(matches(hasChildCount(1)))

        onView(withId(R.id.rvGenreChips)).perform(RecyclerViewActions.actionOnItemAtPosition<GenreChipViewHolder>(1, click()))
        onView(withId(R.id.rvLibraryBooks)).check(matches(hasChildCount(1)))

        onView(withId(R.id.rvGenreChips)).perform(RecyclerViewActions.actionOnItemAtPosition<GenreChipViewHolder>(2, click()))
        onView(withId(R.id.rvLibraryBooks)).check(matches(hasChildCount(1)))

        onView(withId(R.id.btnUnselectAll)).perform(click())
        onView(withId(R.id.rvLibraryBooks)).check(matches(hasChildCount(3)))
    }
}