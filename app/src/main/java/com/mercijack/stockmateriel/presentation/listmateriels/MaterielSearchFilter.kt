package com.mercijack.stockmateriel.presentation.listmateriels

import com.mercijack.stockmateriel.domain.Materiel
import com.mercijack.stockmateriel.presentation.ITextSearchFilter
import org.apache.commons.lang3.StringUtils
import java.util.*

class MaterielSearchFilter : ITextSearchFilter<Materiel> {
    override fun shouldBeDisplayed(constraint: CharSequence?, obj: Materiel): Boolean {
        if (StringUtils.isBlank(constraint)) return true

        val query = constraint.toString().toLowerCase(Locale.ROOT)
        return obj.name.toLowerCase(Locale.ROOT).contains(query) || obj.code.toLowerCase(Locale.ROOT).contains(query)
    }

}