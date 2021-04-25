package com.mercijack.stockmateriel.presentation.home

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

class HomeViewModelUnitTest {

    @get:Rule(order = 0)
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule(order = 1)
    val coroutineRule = MainCoroutineRule()

    private lateinit var viewModel: HomeViewModel
    private lateinit var interactors: Interactors

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        interactors = mock(Interactors::class.java)
        viewModel = HomeViewModel(interactors)
    }

    @Test
    fun onBtnAddClicked() {
        viewModel.onBtnAddClicked()
        assertEquals(true, LiveDataTestUtil.getValue(viewModel.navigateToAddMateriel))
    }

    @Test
    fun doneNavigateToAddMateriel() {
        viewModel.doneNavigateToAddMateriel()
        assertEquals(false, LiveDataTestUtil.getValue(viewModel.navigateToAddMateriel))
    }

    @Test
    fun onBtnViewListClicked() {
        viewModel.onBtnViewListClicked()
        assertEquals(true, LiveDataTestUtil.getValue(viewModel.navigateToMaterielsList))
    }

    @Test
    fun doneNavigateToMaterielsList() {
        viewModel.doneNavigateToMaterielsList()
        assertEquals(false, LiveDataTestUtil.getValue(viewModel.navigateToMaterielsList))
    }

}