package com.mercijack.stockmateriel.presentation.listmateriels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mercijack.stockmateriel.domain.Materiel
import com.mercijack.stockmateriel.framework.Interactors
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MaterielItemViewModel() : ViewModel() {
    val LOG_TAG = MaterielItemViewModel::class.simpleName

    private val _item: MutableLiveData<Materiel> = MutableLiveData()
    val item: LiveData<Materiel> get() = _item

    fun setItem(materiel: Materiel) {
        _item.value = materiel
    }

}