package com.mercijack.stockmateriel.presentation.listmateriels

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mercijack.stockmateriel.databinding.FragmentMaterielsListBinding
import com.mercijack.stockmateriel.domain.Materiel
import com.mercijack.stockmateriel.presentation.IDiffItemCallback
import com.mercijack.stockmateriel.presentation.ITextSearchFilter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MaterielsListFragment : Fragment() {
    val LOG_TAG = MaterielsListFragment::class.simpleName

    private lateinit var dataBinding: FragmentMaterielsListBinding
    private lateinit var adapter: MaterielsListRecyAdapter

    private val viewModel: MaterielsListViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        dataBinding = FragmentMaterielsListBinding.inflate(layoutInflater, container, false)
        dataBinding.lifecycleOwner = viewLifecycleOwner

        dataBinding.viewModel = viewModel
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.show()
        actionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        actionBar?.setCustomView(R.layout.custom_actionbar)

        val window = activity?.window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            //window?.insetsController?.show(WindowInsets.Type.statusBars())
            window?.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window?.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }*/

        adapter = MaterielsListRecyAdapter(requireContext(), viewModel.materielsList.value, object : ITextSearchFilter<Materiel> {
            override fun shouldBeDisplayed(constraint: CharSequence?, obj: Materiel): Boolean {
                if (constraint == null || constraint.isEmpty()) return true

                val name = obj.name
                if (name.isEmpty()) return true

                val query = constraint.toString().toLowerCase()
                return (name.toLowerCase().contains(query))
            }
        }, object : IDiffItemCallback<Materiel> {
            override fun areItemsTheSame(oldItem: Materiel, newItem: Materiel): Boolean {
                return oldItem.code == newItem.code
            }

            override fun areContentsTheSame(oldItem: Materiel, newItem: Materiel): Boolean {
                return oldItem == newItem
            }
        }, viewModel, viewLifecycleOwner)

        val recyclerView = dataBinding.recyAllItems
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = adapter

        viewModel.materielsList.observe(viewLifecycleOwner, { allItems ->
            adapter.submitList(allItems)
        })

        viewModel.clickedItem.observe(viewLifecycleOwner, {
            it?.apply {
                /*if (it.isVideo) {
                    val action = ItemsCollectionFragmentDirections.actionItemsCollectionToPlayerVideo()
                    action.urlVideo = it.url.toString()
                    findNavController().navigate(action)
                    viewModel.doneNavigating()
                } else {
                    val action = ItemsCollectionFragmentDirections.actionItemsCollectionToStoryDetails()
                    action.itemId = it.id
                    findNavController().navigate(action)
                    viewModel.doneNavigating()
                }*/
            }
        })
    }
}