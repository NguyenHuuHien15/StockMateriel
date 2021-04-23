package com.mercijack.stockmateriel.presentation.materielinfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mercijack.stockmateriel.domain.Materiel
import com.mercijack.stockmateriel.framework.Interactors
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MaterielInfoViewModel @Inject constructor(private val interactors: Interactors) : ViewModel() {

    private val _materiel = MutableLiveData<Materiel>()
    val materiel: LiveData<Materiel> get() = _materiel

    private val _backToPrevious = MutableLiveData<Boolean>()
    val backToPrevious: LiveData<Boolean> get() = _backToPrevious

    init {
        _backToPrevious.value = false
    }

    fun setMaterielCode(code: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _materiel.postValue(interactors.getMaterielByCode(code))
        }
    }

    fun onBackClicked() {
        _backToPrevious.value = true
    }

    fun doneBackToPrevious() {
        // Reinit
        _backToPrevious.value = false
    }

    fun onBtnRemoveClicked() {
        viewModelScope.launch(Dispatchers.IO) {
            materiel.value?.let {
                interactors.removeMateriel(it)
                _backToPrevious.postValue(true)
            }
        }
    }

}
