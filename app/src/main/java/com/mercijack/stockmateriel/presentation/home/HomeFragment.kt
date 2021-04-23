package com.mercijack.stockmateriel.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mercijack.stockmateriel.R
import com.mercijack.stockmateriel.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var dataBinding: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        dataBinding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        dataBinding.lifecycleOwner = viewLifecycleOwner
        dataBinding.viewModel = viewModel
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = "Gestion du stock"

        viewModel.navigateToAddMateriel.observe(viewLifecycleOwner, {
            if (it == true) {
                viewModel.doneNavigateToAddMateriel()
                findNavController().navigate(R.id.action_Home_to_AddMateriel)
            }
        })

        viewModel.navigateToMaterielsList.observe(viewLifecycleOwner, {
            if (it == true) {
                viewModel.doneNavigateToMaterielsList()
                findNavController().navigate(R.id.action_Home_to_MaterielsList)
            }
        })

        viewModel.updateNumberMateriels()
    }
}