package com.mercijack.stockmateriel.presentation.addmateriel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mercijack.stockmateriel.LiveDataTestUtil
import com.mercijack.stockmateriel.MainCoroutineRule
import com.mercijack.stockmateriel.helper.db
import com.mercijack.stockmateriel.helper.interactors
import com.mercijack.stockmateriel.helper.materielDao
import com.mercijack.stockmateriel.helper.setUpInteractors
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

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class AddMaterielViewModelInstrumentedTest {

    @get:Rule
    var rules = RuleChain.outerRule(HiltAndroidRule(this))
        .around(InstantTaskExecutorRule())
        .around(MainCoroutineRule())

    private lateinit var viewModel: AddMaterielViewModel

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        setUpInteractors()
        viewModel = AddMaterielViewModel(interactors)
    }

    @ExperimentalCoroutinesApi
    @After
    fun closeDb() {
        db.close()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun onBtnAddClicked_addNewMateriel() = runBlockingTest {
        viewModel.onBtnAddClicked()

        assertEquals(false, LiveDataTestUtil.getValue(viewModel.isMaterielExistedInDb))
        assertEquals(true, LiveDataTestUtil.getValue(viewModel.addSuccess))
    }

    @ExperimentalCoroutinesApi
    @Test
    fun onBtnAddClicked_addMaterielSameCode() = runBlockingTest {
        materielDao.addMateriel(materielEntity1)

        viewModel.updateCode(materielEntity1.code)
        viewModel.updateName("m11")

        viewModel.onBtnAddClicked()

        assertEquals(true, LiveDataTestUtil.getValue(viewModel.isMaterielExistedInDb))
    }
}