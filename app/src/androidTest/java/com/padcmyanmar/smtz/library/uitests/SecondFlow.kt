package com.padcmyanmar.smtz.library.uitests

import android.content.Intent
import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.padcmyanmar.smtz.library.R
import com.padcmyanmar.smtz.library.activities.MainScreenActivity
import com.padcmyanmar.smtz.library.uitests.utils.first
import com.padcmyanmar.smtz.library.views.viewholders.BookListViewHolder
import com.padcmyanmar.smtz.library.views.viewholders.ShelfViewHolder
import com.padcmyanmar.smtz.library.views.viewholders.SmallGridViewViewHolder
import kotlinx.android.synthetic.main.activity_add_to_shelf.*
import kotlinx.android.synthetic.main.view_holder_add_to_shelf.view.*
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4ClassRunner::class)
class SecondFlow {
    private val activityTestRule = ActivityTestRule<MainScreenActivity>(MainScreenActivity::class.java)

    @Before
    open fun setUp() {
        activityTestRule.launchActivity(Intent())
    }

    @Test
    fun test1ShelfEmptyView(){
        onView(withId(R.id.action_library)).perform(click())
        onView(withText(TEST_YOUR_SHELF)).perform(click())
        onView(withId(R.id.vpEmpty)).check(matches(isDisplayed()))
    }

    @Test
    fun test2CreateNewShelf(){

        onView(withId(R.id.action_library)).perform(click())
        onView(withText(TEST_YOUR_SHELF)).perform(click())

        onView(withId(R.id.btnCreateNew)).perform(click())
        onView(withId(R.id.etShelfName)).perform(typeText(TEST_SHELF_NAME), closeSoftKeyboard())

        onView(withId(R.id.btnConfirmCreateShelf)).perform(click())

        onView(withId(R.id.rvShelfList)).check(matches(hasChildCount(1)))
    }

    @Test
    fun test3AddBookOneToShelf(){

        onView(withId(R.id.action_library)).perform(click())
        onView(withText(TEST_YOUR_BOOK)).perform(click())

//        onView(withId(R.id.rvLibraryBooks))
//            .perform(RecyclerViewActions.actionOnItemAtPosition<SmallGridViewViewHolder>(0, scrollTo()))

        onView(first<View>(withId(R.id.btnLibraryMoreActionGrid))).perform(click())
        onView(withId(R.id.llAddtoShelvesListView)).perform(click())
        onView(first<View>(withId(R.id.cbShelf))).perform(click())
//        onView(withId(R.id.rvShelfList)).perform(RecyclerViewActions.actionOnItemAtPosition<ShelfViewHolder>(0, click()))
        onView(withId(R.id.btnConfirmAddToShelf)).perform(click())


        onView(withText(TEST_YOUR_SHELF)).perform(click())
        onView(withId(R.id.rvShelfList)).perform(RecyclerViewActions.actionOnItemAtPosition<ShelfViewHolder>(0, click()))
        onView(withId(R.id.rvLibraryBooks)).check(matches(hasChildCount(1)))

    }

    @Test
    fun test4TapShelf_navigateToShelfDetail(){

        onView(withId(R.id.action_library)).perform(click())
        onView(withText(TEST_YOUR_SHELF)).perform(click())
        onView(withId(R.id.rvShelfList)).perform(RecyclerViewActions.actionOnItemAtPosition<ShelfViewHolder>(0, click()))
        onView(withId(R.id.tvShelfNameShelfDetail)).check(matches(withText(TEST_SHELF_NAME)))
    }

    @Test
    fun test5CheckShelfRename(){

        onView(withId(R.id.action_library)).perform(click())
        onView(withText(TEST_YOUR_SHELF)).perform(click())
        onView(withId(R.id.rvShelfList)).perform(RecyclerViewActions.actionOnItemAtPosition<ShelfViewHolder>(0, click()))
        onView(withId(R.id.btnMoreActionShelfDetail)).perform(click())
        onView(withId(R.id.llRenameShelf)).perform(click())
        onView(withId(R.id.etShelfReName)).perform(typeText(TEST_SHELF_RENAME))
        onView(withId(R.id.etShelfReName)).perform(pressImeActionButton())
        onView(withId(R.id.btnBack)).perform(click())

        onView(first<View>(withId(R.id.tvShelfName))).check(matches(withText("$TEST_SHELF_NAME$TEST_SHELF_RENAME")))
    }

    @Test
    fun test6CheckShelfDelete(){

        onView(withId(R.id.action_library)).perform(click())
        onView(withText(TEST_YOUR_SHELF)).perform(click())
        onView(withId(R.id.rvShelfList)).perform(RecyclerViewActions.actionOnItemAtPosition<ShelfViewHolder>(0, click()))
        onView(withId(R.id.btnMoreActionShelfDetail)).perform(click())
        onView(withId(R.id.llDeleteShelf)).perform(click())
        onView(withId(R.id.vpEmpty)).check(matches(isDisplayed()))

    }

    companion object {
        const val TEST_YOUR_SHELF = "Your shelves"
        const val TEST_YOUR_BOOK = "Your books"
        const val TEST_SHELF_NAME = "New Shelf"
        const val TEST_SHELF_RENAME = " Rename"
    }

}