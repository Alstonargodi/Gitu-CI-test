package com.example.githubuser

import android.view.KeyEvent
import androidx.test.core.app.ActivityScenario
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Before
import org.junit.Test
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.junit.runner.RunWith
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import com.example.githubuser.presentation.activity.MainActivity
import com.example.githubuser.presentation.fragment.home.adapter.UserListRecAdapter

@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
class MainUiTest {
    private val dummyUser = "budi"

    @Before
    fun setup(){
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun detailUserDefault(){
        onView(withId(R.id.RecviewUser)).check(matches(isDisplayed()))
        Thread.sleep(1000)
        onView(withId(R.id.RecviewUser))
            .perform(RecyclerViewActions.actionOnItemAtPosition<UserListRecAdapter.ViewHolder>(0, click()))
        Thread.sleep(1000)
        onView(withId(R.id.detailFragment)).check(matches(isDisplayed()))
    }

    @Test
    fun searchViewInput(){
        onView(withId(R.id.search)).perform(click())
        onView(withId(androidx.appcompat.R.id.search_src_text))
            .perform(typeText(dummyUser))
            .perform(pressKey(KeyEvent.KEYCODE_ENTER))
        Thread.sleep(2000)
        onView(withId(R.id.RecviewUser))
            .check(matches(isDisplayed()))
            .perform(RecyclerViewActions.actionOnItemAtPosition<UserListRecAdapter.ViewHolder>(0, click()))
        Thread.sleep(1000)
        onView(withId(R.id.detailFragment)).check(matches(isDisplayed()))
        onView(withId(R.id.Followviewpager)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_favorite)).perform(click())
        Thread.sleep(1000)
    }




}