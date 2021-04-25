package com.mercijack.stockmateriel.presentation.listmateriels

import com.mercijack.stockmateriel.domain.Materiel
import com.mercijack.stockmateriel.presentation.IDiffItemCallback

class MaterielDiffCallback : IDiffItemCallback<Materiel> {

    override fun areItemsTheSame(oldItem: Materiel, newItem: Materiel): Boolean {
        return oldItem.code == newItem.code
    }

    override fun areContentsTheSame(oldItem: Materiel, newItem: Materiel): Boolean {
        return oldItem == newItem
    }
}