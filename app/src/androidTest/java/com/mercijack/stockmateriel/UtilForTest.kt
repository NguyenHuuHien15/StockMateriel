package com.mercijack.stockmateriel

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Description
import org.hamcrest.Matcher

fun atPosition(position: Int, itemMatcher: Matcher<View?>): Matcher<View?> {
    checkNotNull(itemMatcher)
    return object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {
        override fun describeTo(description: Description) {
            description.appendText("has item at position $position: ")
            itemMatcher.describeTo(description)
        }

        override fun matchesSafely(view: RecyclerView): Boolean {
            val viewHolder = view.findViewHolderForAdapterPosition(position)
                ?: // has no item on such position
                return false
            return itemMatcher.matches(viewHolder.itemView)
        }
    }
}

fun addMateriel1() {
    addMateriel("m1", "c1")
}

fun addMateriel2() {
    addMateriel("m2", "c2")
}

fun addMateriel3SameCodeWithMateriel2() {
    addMateriel("m3", "c2")
}

private fun addMateriel(name: String, code: String) {
    // On add materiel screen
    onView(withId(R.id.tf_name))
        .perform(ViewActions.typeText(name), ViewActions.closeSoftKeyboard())
    onView(withId(R.id.tf_code))
        .perform(ViewActions.typeText(code), ViewActions.closeSoftKeyboard())
    // Click add to add materiel
    onView(withId(R.id.btn_add)).perform(click())
}

