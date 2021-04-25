package com.mercijack.stockmateriel.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mercijack.stockmateriel.LiveDataTestUtil
import com.mercijack.stockmateriel.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    @get:Rule(order = 0)
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule(order = 1)
    val coroutineRule = MainCoroutineRule()

    private lateinit var viewModel: MainViewModel

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        viewModel = MainViewModel()
    }

    @Test
    fun setFullScreen() {
        viewModel.setFullScreen(true)
        assertEquals(true, LiveDataTestUtil.getValue(viewModel.isFullScreen))

        viewModel.setFullScreen(false)
        assertEquals(false, LiveDataTestUtil.getValue(viewModel.isFullScreen))
    }

    @Test
    fun setOnHomeFragment() {
        viewModel.setOnHomeFragment(true)
        assertEquals(true, LiveDataTestUtil.getValue(viewModel.onHomeFragment))

        viewModel.setOnHomeFragment(false)
        assertEquals(false, LiveDataTestUtil.getValue(viewModel.onHomeFragment))
    }
}