package com.mercijack.stockmateriel.presentation.addmateriel

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mercijack.stockmateriel.R
import com.mercijack.stockmateriel.helper.addMateriel1
import com.mercijack.stockmateriel.helper.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest

class AddMaterielFragmentTest {
    @get:Rule
    var rule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        rule.inject()
    }

    @Test
    fun clickAddBtn_back_HomeFragment() {
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
    }
}