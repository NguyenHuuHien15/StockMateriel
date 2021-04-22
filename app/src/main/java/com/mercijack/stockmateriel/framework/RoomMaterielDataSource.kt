package com.mercijack.stockmateriel.framework

import com.mercijack.stockmateriel.data.IMaterielDataSource
import com.mercijack.stockmateriel.domain.Materiel
import com.mercijack.stockmateriel.framework.db.MaterielDao
import com.mercijack.stockmateriel.framework.db.MaterielEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoomMaterielDataSource @Inject constructor(private val materielDao: MaterielDao) : IMaterielDataSource {
    override suspend fun add(materiel: Materiel) {
        materielDao.addMateriel(MaterielEntity(materiel.code, materiel.name))
    }

    override suspend fun remove(materiel: Materiel) {
        materielDao.removeMateriel(MaterielEntity(materiel.code, materiel.name))
    }

    override suspend fun readAll(): List<Materiel> {
        return materielDao.getMateriels().map { Materiel(it.code, it.name) }
    }
}