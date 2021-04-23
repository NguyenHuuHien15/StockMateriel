package com.mercijack.stockmateriel.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mercijack.stockmateriel.framework.Interactors
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val interactors: Interactors) : ViewModel() {

    private val _navigateToAddMateriel = MutableLiveData<Boolean>().apply {
        value = false
    }
    val navigateToAddMateriel: LiveData<Boolean> = _navigateToAddMateriel

    private val _navigateToMaterielsList = MutableLiveData<Boolean>().apply {
        value = false
    }
    val navigateToMaterielsList: LiveData<Boolean> = _navigateToMaterielsList

    private val _numberMateriels = MutableLiveData<Int>().apply {
        viewModelScope.launch(Dispatchers.IO) {
            postValue(interactors.getAllMateriels.invoke().size)
        }
    }
    val numberMateriels: LiveData<Int> = _numberMateriels

    fun updateNumberMateriels() {
        viewModelScope.launch(Dispatchers.IO) {
            _numberMateriels.postValue(interactors.getAllMateriels.invoke().size)
        }
    }

    fun onBtnAddClicked() {
        _navigateToAddMateriel.value = true
    }

    fun doneNavigateToAddMateriel() {
        _navigateToAddMateriel.value = false
    }

    fun onBtnViewListClicked() {
        _navigateToMaterielsList.value = true
    }

    fun doneNavigateToMaterielsList() {
        _navigateToMaterielsList.value = false
    }
}