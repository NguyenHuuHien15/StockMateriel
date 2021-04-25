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
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MaterielInfoViewModel @Inject constructor(private val interactors: Interactors) : ViewModel() {

    private val _materiel = MutableLiveData<Materiel>()
    val materiel: LiveData<Materiel> get() = _materiel

    private val _backToPrevious = MutableLiveData<Boolean>()
    val backToPrevious: LiveData<Boolean> get() = _backToPrevious

    private val _removeSuccess = MutableLiveData<Boolean>()
    val removeSuccess: LiveData<Boolean> get() = _removeSuccess

    init {
        _backToPrevious.value = false
        _removeSuccess.value = false
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
        if(materiel.value == null) return

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                interactors.removeMateriel(materiel.value!!)
            }
            _backToPrevious.postValue(true)
            _removeSuccess.postValue(true)
        }
    }

}
