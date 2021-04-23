package com.mercijack.stockmateriel.framework.db

import androidx.room.*

@Dao
interface MaterielDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMateriel(materiel: MaterielEntity)

    @Delete
    suspend fun removeMateriel(materiel: MaterielEntity)

    @Query("SELECT * FROM materiel_entity_table")
    fun getMateriels(): List<MaterielEntity>

    @Query("SELECT * FROM materiel_entity_table WHERE code = :key")
    suspend fun getMaterielByCode(key: String): MaterielEntity?

}