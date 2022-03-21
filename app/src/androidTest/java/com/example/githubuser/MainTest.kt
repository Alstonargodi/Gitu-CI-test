package com.example.githubuser

import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.testing.launchFragment
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.githubuser.view.home.HomeFragment
import org.junit.Before
import org.junit.Test
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.junit.runner.RunWith
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.githubuser.view.home.adapter.UserListRecAdapter
import kotlinx.coroutines.android.awaitFrame
import kotlinx.coroutines.delay
import okhttp3.internal.wait

@RunWith(AndroidJUnit4ClassRunner::class)
class MainTest {
    private val dummyUser = "budi"

    @Before
    fun setup(){
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun testFavoritePage(){
        onView(withId(R.id.author)).perform(click())
        onView(withId(R.id.bookfragment)).check(matches(isDisplayed()))
        onView(withId(R.id.Author_pager)).perform(swipeLeft())
    }

    @Test
    fun testSearchView(){
        onView(withId(R.id.search))
            .perform(click())
        onView(withId(androidx.appcompat.R.id.search_src_text))
            .perform(typeText(dummyUser))
            .perform(pressKey(KeyEvent.KEYCODE_ENTER))

        onView(withId(R.id.RecviewUser))
            .check(matches(isDisplayed()))

    }
}