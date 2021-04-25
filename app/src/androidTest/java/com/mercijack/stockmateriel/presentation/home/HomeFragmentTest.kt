package com.mercijack.stockmateriel.presentation.home

import android.content.Context
import android.os.Bundle
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mercijack.stockmateriel.R
import com.mercijack.stockmateriel.framework.db.MaterielDao
import com.mercijack.stockmateriel.framework.db.MaterielDatabase
import com.mercijack.stockmateriel.framework.db.RoomModule
import com.mercijack.stockmateriel.helper.launchFragmentInHiltContainer
import com.mercijack.stockmateriel.materielEntity1
import com.mercijack.stockmateriel.materielEntity2
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@UninstallModules(
    RoomModule::class
)
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class HomeFragmentTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var materielDao: MaterielDao
    private lateinit var db: MaterielDatabase

    @InstallIn(SingletonComponent::class)
    @Module
    inner class RoomModuleForTest {

        @Provides
        @Singleton
        fun providesDatabase(@ApplicationContext context: Context): MaterielDatabase {
            return db
        }

        @Provides
        @Singleton
        fun providesDao(database: MaterielDatabase): MaterielDao {
            return database.materielDao
        }
    }

    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), MaterielDatabase::class.java).build()
        materielDao = db.materielDao

        hiltRule.inject()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun clickAddBtn_navigateTo_AddMaterielFragment() {
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

    @Test
    fun click_ViewStockBtn_navigateTo_MaterielsListFragment() {
        // Prepare: Write materiel in db
        runBlocking {
            materielDao.addMateriel(materielEntity1)
        }

        // GIVEN - On the home screen
        val navController = mock(NavController::class.java)

        launchFragmentInHiltContainer<HomeFragment>(Bundle(), R.style.Theme_StockMateriel) {
            Navigation.setViewNavController(this.view!!, navController)
        }

        onView(withId(R.id.mtv_title)).check(matches(withText("Mon stock")))
        onView(withId(R.id.mtv_number_materiels)).check(matches(withText("1 matériel")))

        // WHEN - Click
        onView(withId(R.id.btn_view_stock)).perform(click())

        // THEN - Verify that we navigate to materiels list screen
        verify(navController).navigate(R.id.action_Home_to_MaterielsList)
    }

    @Test
    fun click_RemoveBtn_navigateTo_MaterielsListFragment() {
        // Prepare: Write materiels in db
        runBlocking {
            materielDao.addMateriel(materielEntity1)
            materielDao.addMateriel(materielEntity2)
        }

        // GIVEN - On the home screen
        val navController = mock(NavController::class.java)

        launchFragmentInHiltContainer<HomeFragment>(Bundle(), R.style.Theme_StockMateriel) {
            Navigation.setViewNavController(this.view!!, navController)
        }

        onView(withId(R.id.mtv_title)).check(matches(withText("Mon stock")))
        onView(withId(R.id.mtv_number_materiels)).check(matches(withText("2 matériels")))

        // WHEN - Click
        onView(withId(R.id.btn_remove)).perform(click())

        // THEN - Verify that we navigate to materiels list screen
        verify(navController).navigate(R.id.action_Home_to_MaterielsList)
    }
}