package com.mercijack.stockmateriel.framework

import com.mercijack.stockmateriel.interactors.AddMateriel
import com.mercijack.stockmateriel.interactors.GetAllMateriels
import com.mercijack.stockmateriel.interactors.GetMaterielByCode
import com.mercijack.stockmateriel.interactors.RemoveMateriel

data class Interactors(
    val addMateriel: AddMateriel,
    val removeMateriel: RemoveMateriel,
    val getAllMateriels: GetAllMateriels,
    val getMaterielByCode: GetMaterielByCode
)