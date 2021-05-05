package com.mercijack.stockmateriel.framework

import com.mercijack.stockmateriel.interactors.AddMateriel
import com.mercijack.stockmateriel.interactors.GetAllMateriels
import com.mercijack.stockmateriel.interactors.GetMaterielByCode
import com.mercijack.stockmateriel.interactors.RemoveMateriel
import javax.inject.Inject

data class Interactors @Inject constructor(
    val addMateriel: AddMateriel,
    val removeMateriel: RemoveMateriel,
    val getAllMateriels: GetAllMateriels,
    val getMaterielByCode: GetMaterielByCode
)