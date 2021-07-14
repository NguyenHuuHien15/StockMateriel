package com.mercijack.stockmateriel.presentation.listmateriels

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.mercijack.stockmateriel.databinding.MaterielItemRecylerviewBinding
import com.mercijack.stockmateriel.domain.Materiel
import com.mercijack.stockmateriel.presentation.GenericRecyListAdapter
import com.mercijack.stockmateriel.presentation.IDiffItemCallback
import com.mercijack.stockmateriel.presentation.ITextSearchFilter

class MaterielsListRecyAdapter(list: MutableList<Materiel>, filter: ITextSearchFilter<Materiel>?, diffCallback: IDiffItemCallback<Materiel>)
    : GenericRecyListAdapter<Materiel>(list, filter, diffCallback) {

    private lateinit var materielsListViewModel: MaterielsListViewModel
    private lateinit var lifecycleOwner: LifecycleOwner

    constructor(
        originalList: MutableList<Materiel>, filter: ITextSearchFilter<Materiel>?, diffCallback: IDiffItemCallback<Materiel>,
        materielsListViewModel: MaterielsListViewModel, lifecycleOwner: LifecycleOwner
    ) : this(originalList, filter, diffCallback) {

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