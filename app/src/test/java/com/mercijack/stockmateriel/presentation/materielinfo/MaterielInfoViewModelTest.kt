package com.mercijack.stockmateriel.presentation.materielinfo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mercijack.stockmateriel.LiveDataTestUtil
import com.mercijack.stockmateriel.MainCoroutineRule
import com.mercijack.stockmateriel.framework.Interactors
import com.mercijack.stockmateriel.interactors.GetMaterielByCode
import com.mercijack.stockmateriel.interactors.RemoveMateriel
import com.mercijack.stockmateriel.materiel1
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when` as whenever

class MaterielInfoViewModelTest {

    @get:Rule(order = 0)
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule(order = 1)
    val coroutineRule = MainCoroutineRule()

    private lateinit var viewModel: MaterielInfoViewModel
    private lateinit var interactors: Interactors
    private lateinit var getMaterielByCode: GetMaterielByCode
    private lateinit var removeMateriel: RemoveMateriel

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        interactors = mock(Interactors::class.java)
        getMaterielByCode = mock(GetMaterielByCode::class.java)
        removeMateriel = mock(RemoveMateriel::class.java)
        viewModel = MaterielInfoViewModel(interactors)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun setMaterielCode() = runBlockingTest {
        whenever(interactors.getMaterielByCode).thenReturn(getMaterielByCode)
        whenever(getMaterielByCode.invoke(materiel1.code)).thenReturn(materiel1)

        viewModel.setMaterielCode(materiel1.code)
        assertEquals(materiel1, LiveDataTestUtil.getValue(viewModel.materiel))
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

    @ExperimentalCoroutinesApi
    @Test
    fun onBtnRemoveClicked_for_NullMateriel() = runBlockingTest {
        // Given
        val materiel = viewModel.materiel.value
        whenever(removeMateriel.invoke(materiel)).thenReturn(Unit)
        whenever(interactors.removeMateriel).thenReturn(removeMateriel)

        // When
        viewModel.onBtnRemoveClicked()

        assertEquals(true, LiveDataTestUtil.getValue(viewModel.backToPrevious))
        assertEquals(true, LiveDataTestUtil.getValue(viewModel.removeSuccess))
    }

    @ExperimentalCoroutinesApi
    @Test
    fun onBtnRemoveClicked_for_RealMateriel() = runBlockingTest {
        // Given
        val materiel = viewModel.materiel.value
        whenever(removeMateriel.invoke(materiel)).thenReturn(Unit)
        whenever(interactors.removeMateriel).thenReturn(removeMateriel)

        whenever(interactors.getMaterielByCode).thenReturn(getMaterielByCode)
        whenever(getMaterielByCode.invoke(materiel1.code)).thenReturn(materiel1)

        viewModel.setMaterielCode(materiel1.code)

        // When
        viewModel.onBtnRemoveClicked()

        assertEquals(true, LiveDataTestUtil.getValue(viewModel.backToPrevious))
        assertEquals(true, LiveDataTestUtil.getValue(viewModel.removeSuccess))
    }
}