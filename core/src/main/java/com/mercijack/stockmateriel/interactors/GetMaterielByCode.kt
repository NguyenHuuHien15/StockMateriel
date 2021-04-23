package com.mercijack.stockmateriel.interactors

import com.mercijack.stockmateriel.data.MaterielRepository
import com.mercijack.stockmateriel.domain.Materiel
import javax.inject.Inject

class GetMaterielByCode @Inject constructor(private val materielRepo: MaterielRepository) {

    suspend operator fun invoke(code : String) = materielRepo.getMaterielByCode(code)

}