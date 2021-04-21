package com.mercijack.stockmateriel.interactors

import com.mercijack.stockmateriel.data.MaterielRepository
import javax.inject.Inject

class GetAllMateriels @Inject constructor(private val materielRepo: MaterielRepository) {

    suspend operator fun invoke() = materielRepo.getMateriels()

}