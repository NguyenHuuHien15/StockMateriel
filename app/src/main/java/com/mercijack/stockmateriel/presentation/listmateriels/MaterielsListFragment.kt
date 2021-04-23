package com.mercijack.stockmateriel.presentation.listmateriels

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mercijack.stockmateriel.databinding.FragmentMaterielsListBinding
import com.mercijack.stockmateriel.presentation.MainActivity
import com.mercijack.stockmateriel.presentation.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MaterielsListFragment : Fragment() {
    val LOG_TAG = MaterielsListFragment::class.simpleName

    private lateinit var dataBinding: FragmentMaterielsListBinding
    private lateinit var adapter: MaterielsListRecyAdapter

    private val viewModel: MaterielsListViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        dataBinding = FragmentMaterielsListBinding.inflate(layoutInflater, container, false)
        dataBinding.lifecycleOwner = viewLifecycleOwner

        dataBinding.viewModel = viewModel
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainActivity = activity as MainActivity
        mainActivity.title = "Liste des matériels"

        mainViewModel.setFullScreen(false)

        adapter = MaterielsListRecyAdapter(
            requireContext(), viewModel.materielsList.value,
            MaterielSearchFilter(), MaterielDiffCallback(), viewModel, viewLifecycleOwner
        )

        val recyclerView = dataBinding.recyAllItems
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = adapter

        viewModel.materielsList.observe(viewLifecycleOwner, { allItems ->
            adapter.setList(allItems)
        })

        viewModel.clickedItem.observe(viewLifecycleOwner, {
            it?.apply {
                val action = MaterielsListFragmentDirections.actionMaterielsListToMaterielInfo()
                action.code = it.code
                findNavController().navigate(action)
                viewModel.doneNavigating()
            }
        })

        viewModel.updateMaterielsList()

        viewModel.removeSuccess.observe(viewLifecycleOwner, {
            if (it == true) Toast.makeText(context, "Matériel retiré en succès", Toast.LENGTH_LONG).show()
        })

        dataBinding.tfSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // do nothing
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.filter.filter(s)
            }

            override fun afterTextChanged(s: Editable?) {
                // do nothing
            }

        })

    }
}