package com.mercijack.stockmateriel.helper

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.mercijack.stockmateriel.R
import com.mercijack.stockmateriel.data.MaterielRepository
import com.mercijack.stockmateriel.framework.Interactors
import com.mercijack.stockmateriel.framework.RoomMaterielDataSource
import com.mercijack.stockmateriel.framework.db.MaterielDao
import com.mercijack.stockmateriel.framework.db.MaterielDatabase
import com.mercijack.stockmateriel.interactors.AddMateriel
import com.mercijack.stockmateriel.interactors.GetAllMateriels
import com.mercijack.stockmateriel.interactors.GetMaterielByCode
import com.mercijack.stockmateriel.interactors.RemoveMateriel
import org.hamcrest.Description
import org.hamcrest.Matcher

lateinit var materielDao: MaterielDao
lateinit var db: MaterielDatabase
lateinit var interactors: Interactors
lateinit var addMateriel: AddMateriel
lateinit var removeMateriel: RemoveMateriel
lateinit var getAllMateriels: GetAllMateriels
lateinit var getMaterielByCode: GetMaterielByCode
lateinit var materielRepository: MaterielRepository
lateinit var roomMaterielDataSource: RoomMaterielDataSource

fun setUpInteractors() {
    db = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), MaterielDatabase::class.java)
        .allowMainThreadQueries()
        .build()
    materielDao = db.materielDao
    roomMaterielDataSource = RoomMaterielDataSource(materielDao)
    materielRepository = MaterielRepository(roomMaterielDataSource)
    addMateriel = AddMateriel(materielRepository)
    removeMateriel = RemoveMateriel(materielRepository)
    getAllMateriels = GetAllMateriels(materielRepository)
    getMaterielByCode = GetMaterielByCode(materielRepository)
    interactors = Interactors(addMateriel, removeMateriel, getAllMateriels, getMaterielByCode)
}

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
    onView(withId(R.id.et_name)).perform(ViewActions.typeText(name), ViewActions.closeSoftKeyboard())
    onView(withId(R.id.et_code)).perform(ViewActions.typeText(code), ViewActions.closeSoftKeyboard())
    // Click add to add materiel
    onView(withId(R.id.btn_add)).perform(click())
}

