package com.mercijack.stockmateriel

import com.mercijack.stockmateriel.domain.Materiel
import com.mercijack.stockmateriel.framework.db.MaterielEntity

val materiel1 = Materiel("c1", "m1")
val materiel2 = Materiel("c2", "m2")

// Copy of materiel1
val materiel3 = Materiel("c1", "m1")

// Same code with materiel 1
val materiel4 = Materiel("c1", "m4")

val materielEntity1 = MaterielEntity("c1", "m1")
val materielEntity2 = MaterielEntity("c2", "m2")