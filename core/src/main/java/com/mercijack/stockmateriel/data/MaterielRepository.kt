package com.mercijack.stockmateriel.data

import com.mercijack.stockmateriel.domain.Materiel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MaterielRepository @Inject constructor(private val dataSource: IMaterielDataSource) {

    suspend fun addMateriel(materiel: Materiel) = dataSource.add(materiel)

    suspend fun removeMateriel(code: String) = dataSource.remove(code)

    suspend fun getMateriels() = dataSource.readAll()

}