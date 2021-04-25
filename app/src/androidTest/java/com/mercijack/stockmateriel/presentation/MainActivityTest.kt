package com.mercijack.stockmateriel.presentation

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItem
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mercijack.stockmateriel.R
import com.mercijack.stockmateriel.helper.addMateriel1
import com.mercijack.stockmateriel.helper.addMateriel2
import com.mercijack.stockmateriel.helper.addMateriel3SameCodeWithMateriel2
import com.mercijack.stockmateriel.helper.atPosition
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class MainActivityTest {

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
    fun addMaterielSuccess() {
        ActivityScenario.launch(MainActivity::class.java)

        // 1. Add a materiel
        onView(withId(R.id.btn_add)).perform(click())

        addMateriel1()
        // Verify msg add materiel success
        onView(withText(R.string.add_materiel_success))
            .inRoot(RootMatchers.withDecorView(Matchers.not(decorView)))
            .check(matches(isDisplayed()))

        Thread.sleep(5000)

        // 2. On home screen, add an other materiel
        onView(withId(R.id.btn_add)).perform(click())

        addMateriel2()
        // Verify msg add materiel success
        onView(withText(R.string.add_materiel_success))
            .inRoot(RootMatchers.withDecorView(Matchers.not(decorView)))
            .check(matches(isDisplayed()))
    }

    @Test
    fun addMaterielSameCodeNoSuccess() {
        ActivityScenario.launch(MainActivity::class.java)

        // 1. On home screen, add a materiel
        onView(withId(R.id.btn_add)).perform(click())

        addMateriel2()

        // Sleep 5s to wait the first toast finish
        Thread.sleep(5000)

        // 2. On home screen, add an other materiel
        onView(withId(R.id.btn_add)).perform(click())

        addMateriel3SameCodeWithMateriel2()
        // Verify msg add materiel non success because the code is existed
        onView(withText(R.string.code_existed_materiel_not_added))
            .inRoot(RootMatchers.withDecorView(Matchers.not(decorView)))
            .check(matches(isDisplayed()))
    }

    @Test
    fun viewStockMateriels() {
        ActivityScenario.launch(MainActivity::class.java)

        // 1. Add a materiel
        onView(withId(R.id.btn_add)).perform(click())
        addMateriel1()

        // 2. On home screen, add an other materiel
        onView(withId(R.id.btn_add)).perform(click())
        addMateriel2()

        // 3. View list
        onView(withId(R.id.btn_view_stock)).perform(click())
        onView(withId(R.id.til_search)).check(matches(isDisplayed()))
        onView(withId(R.id.recy_all_items)).check(matches(isDisplayed()))
        onView(withId(R.id.recy_all_items)).check(matches(atPosition(0, hasDescendant(withText("m1")))))
        onView(withId(R.id.recy_all_items)).check(matches(atPosition(0, hasDescendant(withText("c1")))))
        onView(withId(R.id.recy_all_items)).perform(scrollToPosition<RecyclerView.ViewHolder>(1))
        onView(withId(R.id.recy_all_items)).check(matches(atPosition(1, hasDescendant(withText("m2")))))
        onView(withId(R.id.recy_all_items)).check(matches(atPosition(1, hasDescendant(withText("c2")))))
    }

    @Test
    fun viewMaterielInfo() {
        ActivityScenario.launch(MainActivity::class.java)

        // 1. Add a materiel
        onView(withId(R.id.btn_add)).perform(click())
        addMateriel1()

        // 2. On home screen, add an other materiel
        onView(withId(R.id.btn_add)).perform(click())
        addMateriel2()

        // 3. View list
        onView(withId(R.id.btn_view_stock)).perform(click())
        onView(withId(R.id.recy_all_items)).perform(actionOnItem<RecyclerView.ViewHolder>(hasDescendant(withText("m1")), click()))

        // 4. On materiel info screen
        onView(withId(R.id.tv_name)).check(matches(withText("m1")))
        onView(withId(R.id.tv_code)).check(matches(withText("c1")))
        onView(withId(R.id.img_btn_back)).check(matches(isDisplayed()))
        onView(withId(R.id.img_btn_remove)).check(matches(isDisplayed()))
    }

    @Test
    fun removeMaterielFromInfoScreen() {
        ActivityScenario.launch(MainActivity::class.java)

        // 1. Add a materiel
        onView(withId(R.id.btn_add)).perform(click())
        addMateriel1()

        // 2. On home screen, add an other materiel
        onView(withId(R.id.btn_add)).perform(click())
        addMateriel2()

        // 3. View list
        onView(withId(R.id.btn_view_stock)).perform(click())
        onView(withId(R.id.recy_all_items)).perform(actionOnItem<RecyclerView.ViewHolder>(hasDescendant(withText("m1")), click()))

        // 4. On materiel info screen, click btn remove materiel 1
        onView(withId(R.id.img_btn_remove)).perform(click())

        // 5. On list screen, back
        onView(withId(R.id.recy_all_items)).check(matches(isDisplayed()))
        onView(withId(R.id.recy_all_items)).check(matches(atPosition(0, hasDescendant(withText("m2")))))
        onView(withId(R.id.recy_all_items)).check(matches(atPosition(0, hasDescendant(withText("c2")))))
        pressBack()

        // 6. On home screen
        onView(withId(R.id.mtv_number_materiels)).check(matches(withText("1 matériel")))
    }

}