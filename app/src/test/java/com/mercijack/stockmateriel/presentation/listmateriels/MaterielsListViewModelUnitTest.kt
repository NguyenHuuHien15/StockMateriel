package com.mercijack.stockmateriel.presentation.listmateriels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mercijack.stockmateriel.LiveDataTestUtil
import com.mercijack.stockmateriel.MainCoroutineRule
import com.mercijack.stockmateriel.framework.Interactors
import com.mercijack.stockmateriel.materiel1
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MaterielsListViewModelUnitTest {

    @get:Rule(order = 0)
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule(order = 1)
    val coroutineRule = MainCoroutineRule()

    private lateinit var viewModel: MaterielsListViewModel
    private lateinit var interactors: Interactors

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        interactors = mockk()
        viewModel = MaterielsListViewModel(interactors)
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

}