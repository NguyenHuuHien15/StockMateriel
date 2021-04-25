package com.mercijack.stockmateriel.presentation.addmateriel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mercijack.stockmateriel.LiveDataTestUtil
import com.mercijack.stockmateriel.MainCoroutineRule
import com.mercijack.stockmateriel.framework.Interactors
import com.mercijack.stockmateriel.materiel1
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

class AddMaterielViewModelUnitTest {

    @get:Rule(order = 0)
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule(order = 1)
    val coroutineRule = MainCoroutineRule()

    private lateinit var viewModel: AddMaterielViewModel
    private lateinit var interactors: Interactors

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        interactors = mock(Interactors::class.java)
        viewModel = AddMaterielViewModel(interactors)
    }

    @Test
    fun updateCode() {
        viewModel.updateCode(materiel1.code)
        assertEquals(materiel1.code, LiveDataTestUtil.getValue(viewModel.code))
    }

    @Test
    fun updateName() {
        viewModel.updateName(materiel1.name)
        assertEquals(materiel1.name, LiveDataTestUtil.getValue(viewModel.name))
    }

}