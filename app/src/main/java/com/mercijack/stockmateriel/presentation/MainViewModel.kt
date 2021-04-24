package com.mercijack.stockmateriel.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _isFullScreen = MutableLiveData<Boolean>().apply {
        value = false
    }
    val isFullScreen: LiveData<Boolean> = _isFullScreen

    fun setFullScreen(value: Boolean) {
        _isFullScreen.value = value
    }

    private val _onHomeFragment = MutableLiveData<Boolean>().apply {
        value = true
    }
    val onHomeFragment: LiveData<Boolean> = _onHomeFragment

    fun setOnHomeFragment(value: Boolean) {
        _onHomeFragment.value = value
    }
}