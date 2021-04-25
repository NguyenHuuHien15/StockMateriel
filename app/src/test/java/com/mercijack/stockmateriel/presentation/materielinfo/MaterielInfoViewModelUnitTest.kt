package com.mercijack.stockmateriel.presentation.materielinfo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mercijack.stockmateriel.LiveDataTestUtil
import com.mercijack.stockmateriel.MainCoroutineRule
import com.mercijack.stockmateriel.framework.Interactors
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

class MaterielInfoViewModelUnitTest {

    @get:Rule(order = 0)
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule(order = 1)
    val coroutineRule = MainCoroutineRule()

    private lateinit var viewModel: MaterielInfoViewModel
    private lateinit var interactors: Interactors

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        interactors = mock(Interactors::class.java)
        viewModel = MaterielInfoViewModel(interactors)
    }

    @Test
    fun onBackClicked() {
        viewModel.onBackClicked()
        assertEquals(true, LiveDataTestUtil.getValue(viewModel.backToPrevious))
    }

    @Test
    fun doneBackToPrevious() {
        viewModel.doneBackToPrevious()
        assertEquals(false, LiveDataTestUtil.getValue(viewModel.backToPrevious))
    }

}