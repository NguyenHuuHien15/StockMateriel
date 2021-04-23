package com.mercijack.stockmateriel.presentation.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mercijack.stockmateriel.LiveDataTestUtil
import com.mercijack.stockmateriel.framework.Interactors
import com.mercijack.stockmateriel.interactors.GetAllMateriels
import com.mercijack.stockmateriel.materiel1
import com.mercijack.stockmateriel.materiel2
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when` as whenever

class HomeViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: HomeViewModel
    private lateinit var interactors: Interactors
    private lateinit var getAllMateriels: GetAllMateriels

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        interactors = mock(Interactors::class.java)
        getAllMateriels = mock(GetAllMateriels::class.java)
        viewModel = HomeViewModel(interactors)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun updateNumberMateriels() {
        runBlocking {
            whenever(interactors.getAllMateriels).thenReturn(getAllMateriels)
            whenever(getAllMateriels.invoke()).thenReturn(emptyList())
            viewModel.updateNumberMateriels()
            assertEquals(0, LiveDataTestUtil.getValue(viewModel.numberMateriels))
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun updateNumberMateriels2() {
        runBlocking {
            whenever(interactors.getAllMateriels).thenReturn(getAllMateriels)
            whenever(getAllMateriels.invoke()).thenReturn(listOf(materiel1, materiel2))
            viewModel.updateNumberMateriels()
            assertEquals(2, LiveDataTestUtil.getValue(viewModel.numberMateriels))
        }
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