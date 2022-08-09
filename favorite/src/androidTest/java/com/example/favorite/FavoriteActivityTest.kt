package com.example.favorite

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.githubuser.presentation.activity.MainActivity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
class FavoriteActivityTest {
    @Before
    fun setup(){
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun favoritePagetoAuthorDetail(){
        onView(withId(R.id.bookfragment))
            .check(matches(isDisplayed()))
        onView(withId(R.id.RecyclerV_Favorite))
            .check(matches(isDisplayed()))
        Thread.sleep(1000)
        onView(withId(R.id.Author_pager))
            .perform(swipeLeft())
        onView(withId(R.id.Tablayout_author))
            .check(matches(isDisplayed()))
    }
}