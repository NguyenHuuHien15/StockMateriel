package com.mercijack.stockmateriel.presentation.materielinfo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mercijack.stockmateriel.LiveDataTestUtil
import com.mercijack.stockmateriel.MainCoroutineRule
import com.mercijack.stockmateriel.helper.db
import com.mercijack.stockmateriel.helper.interactors
import com.mercijack.stockmateriel.helper.materielDao
import com.mercijack.stockmateriel.helper.setUpInteractors
import com.mercijack.stockmateriel.materiel1
import com.mercijack.stockmateriel.materielEntity1
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
import org.mockito.Mockito

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class MaterielInfoViewModelInstrumentedTest {

    @get:Rule
    var rules = RuleChain.outerRule(HiltAndroidRule(this))
        .around(InstantTaskExecutorRule())
        .around(MainCoroutineRule())

    private lateinit var viewModel: MaterielInfoViewModel

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        setUpInteractors()
        viewModel = MaterielInfoViewModel(interactors)
    }

    @ExperimentalCoroutinesApi
    @After
    fun closeDb() {
        db.close()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun setMaterielCode() = runBlockingTest {
        materielDao.addMateriel(materielEntity1)

        viewModel.setMaterielCode(materiel1.code)

        assertEquals(materiel1, LiveDataTestUtil.getValue(viewModel.materiel))
    }

    @ExperimentalCoroutinesApi
    @Test
    fun onBtnRemoveClicked_for_NullMateriel() = runBlockingTest {
        viewModel.onBtnRemoveClicked()

        assertEquals(true, LiveDataTestUtil.getValue(viewModel.backToPrevious))
        assertEquals(true, LiveDataTestUtil.getValue(viewModel.removeSuccess))
    }

    @ExperimentalCoroutinesApi
    @Test
    fun onBtnRemoveClicked_for_RealMateriel() = runBlockingTest {
        materielDao.addMateriel(materielEntity1)
        viewModel.setMaterielCode(materiel1.code)

        viewModel.onBtnRemoveClicked()

        assertEquals(true, LiveDataTestUtil.getValue(viewModel.backToPrevious))
        assertEquals(true, LiveDataTestUtil.getValue(viewModel.removeSuccess))
    }
}