package com.mercijack.stockmateriel.presentation.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mercijack.stockmateriel.LiveDataTestUtil
import com.mercijack.stockmateriel.MainCoroutineRule
import com.mercijack.stockmateriel.helper.db
import com.mercijack.stockmateriel.helper.interactors
import com.mercijack.stockmateriel.helper.materielDao
import com.mercijack.stockmateriel.helper.setUpInteractors
import com.mercijack.stockmateriel.materielEntity1
import com.mercijack.stockmateriel.materielEntity2
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class HomeViewModelInstrumentedTest {

    @get:Rule
    var rules = RuleChain.outerRule(HiltAndroidRule(this))
        .around(InstantTaskExecutorRule())
        .around(MainCoroutineRule())

    private lateinit var viewModel: HomeViewModel

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        setUpInteractors()
        viewModel = HomeViewModel(interactors)
    }

    @ExperimentalCoroutinesApi
    @After
    fun closeDb() {
        db.close()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun updateNumberMaterielsVides() = runBlockingTest {
        viewModel.updateNumberMateriels()

        assertEquals(0, LiveDataTestUtil.getValue(viewModel.numberMateriels))
    }

    @ExperimentalCoroutinesApi
    @Test
    fun updateNumberMateriels1Element() = runBlockingTest {
        materielDao.addMateriel(materielEntity1)

        viewModel.updateNumberMateriels()

        assertEquals(1, LiveDataTestUtil.getValue(viewModel.numberMateriels))
    }
}