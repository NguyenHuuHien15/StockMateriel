package com.mercijack.stockmateriel.framework.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mercijack.stockmateriel.domain.Materiel

@Entity(tableName = "materiel_entity_table")
data class MaterielEntity constructor(
    @PrimaryKey val code: String,
    val name: String
) {
    fun toDomain(): Materiel {
        return Materiel(code, name)
    }
}