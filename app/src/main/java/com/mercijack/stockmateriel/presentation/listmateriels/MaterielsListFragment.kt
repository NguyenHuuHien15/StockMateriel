package com.mercijack.stockmateriel.presentation.listmateriels

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
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

        activity?.title = "Liste des matériels"

        mainViewModel.setFullScreen(false)
        mainViewModel.setOnHomeFragment(false)

        initRecyclerView()

        createListeners()

        viewModel.updateMaterielsList()

    }

    private fun initRecyclerView() {
        val materielsList = if (viewModel.materielsList.value != null) viewModel.materielsList.value!!.toMutableList() else mutableListOf()
        adapter = MaterielsListRecyAdapter(materielsList, MaterielSearchFilter(), MaterielDiffCallback(), viewModel, viewLifecycleOwner)

        val recyclerView = dataBinding.recyAllItems
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = adapter
    }

    private fun createListeners() {
        viewModel.materielsList.observe(viewLifecycleOwner, { allItems ->
            adapter.updateOriginalList(allItems)
            // Faire la recherche si le cas, ex : après la rotation et le texte de filtrage/recherche est tjs conservé
            val textToSearch = viewModel.textToSearch.value
            Log.d(LOG_TAG, "Text on observer : $textToSearch")
            adapter.filter.filter(textToSearch)
        })

        viewModel.clickedItem.observe(viewLifecycleOwner, {
            it?.apply {
                val action = MaterielsListFragmentDirections.actionMaterielsListToMaterielInfo()
                action.code = it.code
                findNavController().navigate(action)
                viewModel.doneNavigating()
            }
        })

        viewModel.removeSuccess.observe(viewLifecycleOwner, {
            if (it == true) Toast.makeText(context, "Matériel retiré en succès", Toast.LENGTH_LONG).show()
        })

        dataBinding.tfSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // do nothing
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.filter.filter(s)
                viewModel.updateTextToSearch(s.toString())
                // Save to view model
                val textToSearch = viewModel.textToSearch.value
                Log.d(LOG_TAG, "Text onTextChanged : $textToSearch")
            }

            override fun afterTextChanged(s: Editable?) {
                // do nothing
            }

        })
    }

}