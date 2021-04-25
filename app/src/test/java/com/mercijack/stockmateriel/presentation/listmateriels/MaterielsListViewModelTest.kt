package com.mercijack.stockmateriel.presentation.listmateriels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mercijack.stockmateriel.LiveDataTestUtil
import com.mercijack.stockmateriel.MainCoroutineRule
import com.mercijack.stockmateriel.domain.Materiel
import com.mercijack.stockmateriel.framework.Interactors
import com.mercijack.stockmateriel.interactors.GetAllMateriels
import com.mercijack.stockmateriel.interactors.RemoveMateriel
import com.mercijack.stockmateriel.materiel1
import com.mercijack.stockmateriel.materiel2
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MaterielsListViewModelTest {

    @get:Rule(order = 0)
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule(order = 1)
    val coroutineRule = MainCoroutineRule()

    private lateinit var viewModel: MaterielsListViewModel
    private lateinit var interactors: Interactors
    private lateinit var getAllMateriels: GetAllMateriels
    private lateinit var removeMateriel: RemoveMateriel

    @ExperimentalCoroutinesApi
    val dispatcher = TestCoroutineDispatcher()

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        interactors = mockk()
        getAllMateriels = mockk()
        removeMateriel = mockk()
        viewModel = MaterielsListViewModel(interactors)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun updateMaterielsListEmpty() {
        coEvery { getAllMateriels.invoke() } returns listOf()
        coEvery { interactors.getAllMateriels } returns getAllMateriels

        viewModel.updateMaterielsList()
        assertEquals(listOf<Materiel>(), LiveDataTestUtil.getValue(viewModel.materielsList))
    }

    @ExperimentalCoroutinesApi
    @Test
    fun updateMaterielsList2Elements() {
        coEvery { getAllMateriels.invoke() } returns listOf(materiel1, materiel2)
        coEvery { interactors.getAllMateriels } returns getAllMateriels

        viewModel.updateMaterielsList()
        assertEquals(listOf(materiel1, materiel2), LiveDataTestUtil.getValue(viewModel.materielsList))
    }

    @Test
    fun onItemClicked() {
        viewModel.onItemClicked(materiel1)
        assertEquals(materiel1, LiveDataTestUtil.getValue(viewModel.clickedItem))
    }

    @Test
    fun doneNavigating() {
        viewModel.doneNavigating()
        assertEquals(null, LiveDataTestUtil.getValue(viewModel.clickedItem))
    }

    @ExperimentalCoroutinesApi
    @Test
    fun onBtnRemoveClicked() {
        coEvery { getAllMateriels.invoke() } returns listOf(materiel2)
        coEvery { interactors.getAllMateriels } returns getAllMateriels

        coEvery { removeMateriel.invoke(materiel1) } returns Unit
        coEvery { interactors.removeMateriel } returns removeMateriel

        viewModel.onBtnRemoveClicked(materiel1)

        assertEquals(listOf(materiel2), LiveDataTestUtil.getValue(viewModel.materielsList))
        assertEquals(true, LiveDataTestUtil.getValue(viewModel.removeSuccess))
    }
}