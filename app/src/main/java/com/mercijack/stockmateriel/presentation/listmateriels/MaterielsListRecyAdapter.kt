package com.mercijack.stockmateriel.presentation.listmateriels

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.mercijack.stockmateriel.databinding.MaterielItemRecylerviewBinding
import com.mercijack.stockmateriel.domain.Materiel
import com.mercijack.stockmateriel.presentation.GenericRecyListAdapter
import com.mercijack.stockmateriel.presentation.IDiffItemCallback
import com.mercijack.stockmateriel.presentation.ITextSearchFilter

class MaterielsListRecyAdapter(_context: Context, list: List<Materiel>?, filter: ITextSearchFilter<Materiel>?, diffCallback: IDiffItemCallback<Materiel>) : GenericRecyListAdapter<Materiel>(_context, list, filter, diffCallback) {

    private lateinit var materielsListViewModel: MaterielsListViewModel
    private lateinit var lifecycleOwner: LifecycleOwner

    constructor(
        context: Context, list: List<Materiel>?, filter: ITextSearchFilter<Materiel>?, diffCallback: IDiffItemCallback<Materiel>,
        materielsListViewModel: MaterielsListViewModel, lifecycleOwner: LifecycleOwner
    ) : this(context, list, filter, diffCallback) {
        this.materielsListViewModel = materielsListViewModel
        this.lifecycleOwner = lifecycleOwner
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: MaterielItemRecylerviewBinding = MaterielItemRecylerviewBinding.inflate(layoutInflater, parent, false)
        return MyViewHolder(itemBinding)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val holder = viewHolder as MyViewHolder
        val element: Materiel = currentList[position]
        holder.bind(materielsListViewModel, element)
    }

    fun getItemPosition(item: Materiel): Int {
        for (itemForView in currentList) {
            if (itemForView.code == item.code) {
                return currentList.indexOf(itemForView)
            }
        }
        return -1
    }

    inner class MyViewHolder(private val binding: MaterielItemRecylerviewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(materielsListViewModel: MaterielsListViewModel, itemForView: Materiel) {
            binding.lifecycleOwner = lifecycleOwner
            binding.viewModel = materielsListViewModel
            val viewModelBookItem = MaterielItemViewModel()
            viewModelBookItem.setItem(itemForView)
            binding.viewModelItem = viewModelBookItem
            binding.executePendingBindings()
        }
    }
}