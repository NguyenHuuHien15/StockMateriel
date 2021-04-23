package com.mercijack.stockmateriel.framework.db

import com.mercijack.stockmateriel.materiel1
import com.mercijack.stockmateriel.materiel2
import com.mercijack.stockmateriel.materielEntity1
import com.mercijack.stockmateriel.materielEntity2
import org.junit.Assert.assertEquals
import org.junit.Test

class MaterielEntityTest {

    @Test
    fun toDomain() {
        assertEquals(materiel1, materielEntity1.toDomain())
        assertEquals(materiel2, materielEntity2.toDomain())
    }
}