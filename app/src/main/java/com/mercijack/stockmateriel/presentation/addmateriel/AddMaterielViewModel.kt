package com.mercijack.stockmateriel.presentation.addmateriel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mercijack.stockmateriel.domain.Materiel
import com.mercijack.stockmateriel.framework.Interactors
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.apache.commons.lang3.StringUtils
import javax.inject.Inject

@HiltViewModel
class AddMaterielViewModel @Inject constructor(private val interactors: Interactors) : ViewModel() {
    val LOG_TAG = AddMaterielViewModel::class.simpleName

    private val _code = MutableLiveData<String>().apply {
        value = StringUtils.EMPTY
    }
    val code: LiveData<String> get() = _code

    private val _name = MutableLiveData<String>().apply {
        value = StringUtils.EMPTY
    }
    val name: LiveData<String> get() = _name

    fun updateCode(newCode: String) {
        _code.value = newCode
    }

    fun updateName(newName: String) {
        _name.value = newName
    }

    fun onBtnAddClicked() {
        viewModelScope.launch {
            val codeValue = code.value.toString()
            val nameValue = name.value.toString()
            Log.d(LOG_TAG, "Code = $codeValue, name = $nameValue")
            interactors.addMateriel(Materiel(codeValue, nameValue))
        }
    }
}