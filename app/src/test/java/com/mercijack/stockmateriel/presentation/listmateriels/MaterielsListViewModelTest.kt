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
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when` as whenever

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
    @Before
    fun setup() {
        interactors = mock(Interactors::class.java)
        getAllMateriels = mock(GetAllMateriels::class.java)
        removeMateriel = mock(RemoveMateriel::class.java)
        viewModel = MaterielsListViewModel(interactors)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun updateMaterielsListEmpty() = runBlockingTest {
        whenever(getAllMateriels.invoke()).thenReturn(listOf())
        whenever(interactors.getAllMateriels).thenReturn(getAllMateriels)

        viewModel.updateMaterielsList()
        assertEquals(listOf<Materiel>(), LiveDataTestUtil.getValue(viewModel.materielsList))
    }

    @ExperimentalCoroutinesApi
    @Test
    fun updateMaterielsList2Elements() = runBlockingTest {
        whenever(getAllMateriels.invoke()).thenReturn(listOf(materiel1, materiel2))
        whenever(interactors.getAllMateriels).thenReturn(getAllMateriels)

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
    fun onBtnRemoveClicked() = runBlockingTest {
        whenever(getAllMateriels.invoke()).thenReturn(listOf(materiel2))
        whenever(interactors.getAllMateriels).thenReturn(getAllMateriels)

        whenever(removeMateriel.invoke(materiel1)).thenReturn(Unit)
        whenever(interactors.removeMateriel).thenReturn(removeMateriel)

        viewModel.onBtnRemoveClicked(materiel1)

        assertEquals(listOf(materiel2), LiveDataTestUtil.getValue(viewModel.materielsList))
        assertEquals(true, LiveDataTestUtil.getValue(viewModel.removeSuccess))
    }
}