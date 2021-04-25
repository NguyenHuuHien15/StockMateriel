package com.mercijack.stockmateriel.presentation.addmateriel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mercijack.stockmateriel.LiveDataTestUtil
import com.mercijack.stockmateriel.MainCoroutineRule
import com.mercijack.stockmateriel.domain.Materiel
import com.mercijack.stockmateriel.framework.Interactors
import com.mercijack.stockmateriel.interactors.AddMateriel
import com.mercijack.stockmateriel.interactors.GetMaterielByCode
import com.mercijack.stockmateriel.materiel1
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when` as whenever


class AddMaterielViewModelTest {

    @get:Rule(order = 0)
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule(order = 1)
    val coroutineRule = MainCoroutineRule()

    private lateinit var viewModel: AddMaterielViewModel
    private lateinit var interactors: Interactors
    private lateinit var getMaterielByCode: GetMaterielByCode
    private lateinit var addMateriel: AddMateriel

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        interactors = mock(Interactors::class.java)
        getMaterielByCode = mock(GetMaterielByCode::class.java)
        addMateriel = mock(AddMateriel::class.java)
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

    @ExperimentalCoroutinesApi
    @Test
    fun onBtnAddClicked_addNewMateriel() = runBlockingTest {
        whenever(interactors.getMaterielByCode).thenReturn(getMaterielByCode)
        val codeValue = viewModel.code.value.toString()
        val nameValue = viewModel.name.value.toString()
        whenever(getMaterielByCode.invoke(codeValue)).thenReturn(null)
        whenever(interactors.addMateriel).thenReturn(addMateriel)
        whenever(addMateriel.invoke(Materiel(codeValue, nameValue))).thenReturn(Unit)

        viewModel.onBtnAddClicked()
        assertEquals(false, LiveDataTestUtil.getValue(viewModel.isMaterielExistedInDb))
    }

    @ExperimentalCoroutinesApi
    @Test
    fun onBtnAddClicked_addMaterielSameCode() = runBlockingTest {
        whenever(interactors.getMaterielByCode).thenReturn(getMaterielByCode)
        whenever(getMaterielByCode.invoke(viewModel.code.value.toString())).thenReturn(materiel1)

        viewModel.onBtnAddClicked()
        assertEquals(true, LiveDataTestUtil.getValue(viewModel.isMaterielExistedInDb))
    }
}