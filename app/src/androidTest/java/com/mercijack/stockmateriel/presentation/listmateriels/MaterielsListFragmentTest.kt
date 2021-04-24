package com.mercijack.stockmateriel.presentation.listmateriels

import android.content.Context
import android.os.Bundle
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mercijack.stockmateriel.R
import com.mercijack.stockmateriel.framework.db.MaterielDao
import com.mercijack.stockmateriel.framework.db.MaterielDatabase
import com.mercijack.stockmateriel.framework.db.RoomModule
import com.mercijack.stockmateriel.helper.launchFragmentInHiltContainer
import com.mercijack.stockmateriel.materiel1
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
class MaterielsListFragmentTest {

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
    fun clickAMateriel_navigateTo_MaterielInfoFragment() {
        // Prepare: Write 2 materiels in db
        runBlocking {
            materielDao.addMateriel(materielEntity1)
            materielDao.addMateriel(materielEntity2)
        }

        // GIVEN - On the stock screen
        val navController = mock(NavController::class.java)

        launchFragmentInHiltContainer<MaterielsListFragment>(Bundle(), R.style.Theme_StockMateriel) {
            Navigation.setViewNavController(this.view!!, navController)
        }

        onView(withId(R.id.recy_all_items)).check(matches(isDisplayed()))

        // WHEN - Click on a materiel
        onView(withId(R.id.recy_all_items)).perform(RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(hasDescendant(withText("m1")), click()))

        // THEN - Verify that we navigate to materiel add screen
        val action = MaterielsListFragmentDirections.actionMaterielsListToMaterielInfo()
        action.code = materiel1.code
        verify(navController).navigate(action)
    }

}