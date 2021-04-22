package com.mercijack.stockmateriel.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _navigateToAddMateriel = MutableLiveData<Boolean>().apply {
        value = false
    }
    val navigateToAddMateriel: LiveData<Boolean> = _navigateToAddMateriel

    fun onBtnAddClicked() {
        _navigateToAddMateriel.value = true
    }

    fun doneNavigateToAddMateriel() {
        _navigateToAddMateriel.value = false
    }
}