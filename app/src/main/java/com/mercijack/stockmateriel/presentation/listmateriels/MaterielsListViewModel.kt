package com.mercijack.stockmateriel.presentation.listmateriels

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
class MaterielsListViewModel @Inject constructor(private val interactors: Interactors) : ViewModel() {
    val LOG_TAG = MaterielsListViewModel::class.simpleName

    private val _materielsList: MutableLiveData<List<Materiel>> = MutableLiveData()

    val materielsList: LiveData<List<Materiel>> get() = _materielsList

    private val _clickedItem: MutableLiveData<Materiel> = MutableLiveData()
    val clickedItem: LiveData<Materiel> get() = _clickedItem

    private val _removeSuccess = MutableLiveData<Boolean>()
    val removeSuccess: LiveData<Boolean> get() = _removeSuccess

    init {
        _clickedItem.value = null
        _removeSuccess.value = false
        updateMaterielsList()
    }

    fun updateMaterielsList() {
        viewModelScope.launch {
            val list = withContext(Dispatchers.IO) {
                interactors.getAllMateriels.invoke()
            }
            _materielsList.postValue(list)
        }
    }

    fun onItemClicked(item: Materiel) {
        _clickedItem.value = item
    }

    fun doneNavigating() {
        _clickedItem.value = null
    }

    fun onBtnRemoveClicked(materiel: Materiel) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                interactors.removeMateriel(materiel)

                val list = withContext(Dispatchers.IO) {
                    interactors.getAllMateriels.invoke()
                }

                _materielsList.postValue(list)
                _removeSuccess.postValue(true)
            }
        }
    }

}

