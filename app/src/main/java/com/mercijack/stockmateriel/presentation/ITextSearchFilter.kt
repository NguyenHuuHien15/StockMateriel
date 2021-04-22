package com.mercijack.stockmateriel.presentation

interface ITextSearchFilter<K : Any?> {
    fun shouldBeDisplayed(constraint: CharSequence?, obj: K): Boolean
}