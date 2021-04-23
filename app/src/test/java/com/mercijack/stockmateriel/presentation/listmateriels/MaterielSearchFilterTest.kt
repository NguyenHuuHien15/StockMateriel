package com.mercijack.stockmateriel.presentation.listmateriels

import com.mercijack.stockmateriel.materiel1
import org.apache.commons.lang3.StringUtils
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class MaterielSearchFilterTest {

    private lateinit var materielSearchFilter: MaterielSearchFilter
    private var constraint: CharSequence? = null

    @Before
    fun setUp() {
        materielSearchFilter = MaterielSearchFilter()
    }

    @Test
    fun shouldBeDisplayed() {
        constraint = null
        assertTrue(materielSearchFilter.shouldBeDisplayed(constraint, materiel1))

        constraint = StringUtils.EMPTY
        assertTrue(materielSearchFilter.shouldBeDisplayed(constraint, materiel1))

        constraint = "  "
        assertTrue(materielSearchFilter.shouldBeDisplayed(constraint, materiel1))

        constraint = "C1"
        assertTrue(materielSearchFilter.shouldBeDisplayed(constraint, materiel1))

        constraint = "M1"
        assertTrue(materielSearchFilter.shouldBeDisplayed(constraint, materiel1))

        constraint = "c"
        assertTrue(materielSearchFilter.shouldBeDisplayed(constraint, materiel1))

        constraint = "m"
        assertTrue(materielSearchFilter.shouldBeDisplayed(constraint, materiel1))

        constraint = "M11"
        assertFalse(materielSearchFilter.shouldBeDisplayed(constraint, materiel1))

        constraint = "C11"
        assertFalse(materielSearchFilter.shouldBeDisplayed(constraint, materiel1))

    }
}