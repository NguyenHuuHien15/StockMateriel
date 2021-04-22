package com.mercijack.stockmateriel.presentation

import android.content.Context
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import java.util.*

open class GenericRecyListAdapter<K : Any?>(_context: Context, list: List<K>?, private var searchFilter: ITextSearchFilter<K>? = null, diffCallback: IDiffItemCallback<K>)
    : ListAdapter<K, RecyclerView.ViewHolder>(MyDiffCallBack<K>(diffCallback)), Filterable {

    private var list: MutableList<K> // original list = list of non filtered items
    private var adapterFilter: AdapterFilter? = null
    var context: Context

    init {
        if (list != null) {
            this.list = ArrayList(list)
        } else {
            this.list = ArrayList()
        }
        submitList(this.list)
        this.context = _context
    }

    fun clear() {
        list.clear()
        submitList(list)
    }

    override fun getFilter(): Filter {
        if (adapterFilter == null) {
            adapterFilter = AdapterFilter()
        }
        return adapterFilter!!
    }

    fun setList(newList: List<K>?) {
        if (newList != null) {
            list = ArrayList(newList)
            submitList(list)
        }
    }

    private inner class AdapterFilter : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            val filterResults = FilterResults()
            if (constraint.isNotEmpty()) {
                val resultList = ArrayList<K>()
                for (obj in list) {
                    if (shouldBeDisplayed(constraint, obj)) {
                        resultList.add(obj)
                    }
                }
                filterResults.count = resultList.size
                filterResults.values = resultList
            } else {
                filterResults.count = list.size
                filterResults.values = list
            }
            return filterResults
        }

        /**
         * Notify about filtered list to ui
         *
         * @param constraint text
         * @param results    filtered result
         */
        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            submitList(results.values as ArrayList<K>)
        }

        /**
         * @param constraint the text used to filter the K objects list
         * @param obj        the K object eligibile for filtering
         * @return true if the object should be filtered, false otherwise
         */
        private fun shouldBeDisplayed(constraint: CharSequence, obj: K): Boolean {
            return searchFilter == null || searchFilter!!.shouldBeDisplayed(constraint, obj)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    class MyDiffCallBack<K : Any?>(private val diffCallback: IDiffItemCallback<K>) : DiffUtil.ItemCallback<K>() {
        override fun areItemsTheSame(oldItem: K, newItem: K): Boolean {
            return diffCallback.areItemsTheSame(oldItem, newItem)
        }

        override fun areContentsTheSame(oldItem: K, newItem: K): Boolean {
            return diffCallback.areContentsTheSame(oldItem, newItem)
        }

    }

}