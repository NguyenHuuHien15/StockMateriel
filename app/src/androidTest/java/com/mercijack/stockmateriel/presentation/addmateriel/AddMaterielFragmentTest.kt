package com.mercijack.stockmateriel.presentation.addmateriel

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mercijack.stockmateriel.R
import com.mercijack.stockmateriel.helper.addMateriel1
import com.mercijack.stockmateriel.helper.launchFragmentInHiltContainer
import com.mercijack.stockmateriel.presentation.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.Matchers.not
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest

class AddMaterielFragmentTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var activityScenarioRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    private var decorView: View? = null

    @Before
    fun setUp() {
        hiltRule.inject()

        activityScenarioRule.scenario.onActivity { activity ->
            if (activity != null) {
                decorView = activity.window.decorView
            }
        }
    }

    @Test
    fun click_AddBtnSuccess_finish_Fragment() {
        // GIVEN - On the home screen
        val navController = mock(NavController::class.java)

        launchFragmentInHiltContainer<AddMaterielFragment>(Bundle(), R.style.Theme_StockMateriel) {
            Navigation.setViewNavController(this.view!!, navController)
        }

        onView(withId(R.id.et_name)).check(matches(isDisplayed()))
        onView(withId(R.id.et_code)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_add)).check(matches(not(isDisplayed())))

        // THEN - Fullfill name and code and click on the "add" button
        addMateriel1()

        assertEquals(Lifecycle.State.DESTROYED, activityScenarioRule.scenario.state)
    }
}