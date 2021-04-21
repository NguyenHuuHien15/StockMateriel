package com.mercijack.stockmateriel.interactors

import com.mercijack.stockmateriel.data.MaterielRepository
import com.mercijack.stockmateriel.domain.Materiel
import javax.inject.Inject

class AddMateriel @Inject constructor(private val materielRepo: MaterielRepository) {

    suspend operator fun invoke(materiel: Materiel) = materielRepo.addMateriel(materiel)

}