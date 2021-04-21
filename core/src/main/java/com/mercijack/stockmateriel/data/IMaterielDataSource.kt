package com.mercijack.stockmateriel.data

import com.mercijack.stockmateriel.domain.Materiel

interface IMaterielDataSource {

    suspend fun add(materiel: Materiel)

    suspend fun remove(materielCode: String)

    suspend fun readAll(): List<Materiel>

}