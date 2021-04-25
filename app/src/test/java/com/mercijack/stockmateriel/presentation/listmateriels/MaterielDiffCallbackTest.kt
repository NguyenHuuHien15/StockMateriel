package com.mercijack.stockmateriel.presentation.listmateriels

import com.mercijack.stockmateriel.materiel1
import com.mercijack.stockmateriel.materiel2
import com.mercijack.stockmateriel.materiel3
import com.mercijack.stockmateriel.materiel4
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class MaterielDiffCallbackTest {

    private lateinit var materielDiffCallback: MaterielDiffCallback

    @Before
    fun setUp() {
        materielDiffCallback = MaterielDiffCallback()
    }

    @Test
    fun areItemsTheSame() {
        assertTrue(materielDiffCallback.areItemsTheSame(materiel1, materiel1))
        assertTrue(materielDiffCallback.areItemsTheSame(materiel1, materiel3))
        assertTrue(materielDiffCallback.areItemsTheSame(materiel1, materiel4))
        assertFalse(materielDiffCallback.areItemsTheSame(materiel1, materiel2))
    }

    @Test
    fun areContentsTheSame() {
        assertTrue(materielDiffCallback.areContentsTheSame(materiel1, materiel1))
        assertTrue(materielDiffCallback.areContentsTheSame(materiel1, materiel3))
        assertFalse(materielDiffCallback.areContentsTheSame(materiel1, materiel4))
        assertFalse(materielDiffCallback.areContentsTheSame(materiel1, materiel2))
    }
}