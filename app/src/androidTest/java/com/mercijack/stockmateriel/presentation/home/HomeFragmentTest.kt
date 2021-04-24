package com.mercijack.stockmateriel.presentation.home

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mercijack.stockmateriel.R
import com.mercijack.stockmateriel.helper.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class HomeFragmentTest {

    @get:Rule
    var rule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        rule.inject()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun clickAddBtn_navigateToAddMaterielFragment() {
        // GIVEN - On the home screen
        val navController = mock(NavController::class.java)

        launchFragmentInHiltContainer<HomeFragment>(Bundle(), R.style.Theme_StockMateriel) {
            Navigation.setViewNavController(this.view!!, navController)
        }

        onView(withId(R.id.mtv_title)).check(matches(withText("Mon stock")))
        onView(withId(R.id.mtv_number_materiels)).check(matches(withText("Vide")))

        // WHEN - Click on the "add" button
        onView(withId(R.id.btn_add)).perform(click())

        // THEN - Verify that we navigate to materiel add screen
        verify(navController).navigate(R.id.action_Home_to_AddMateriel)
    }
}