package com.mercijack.stockmateriel.presentation.addmateriel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mercijack.stockmateriel.databinding.FragmentAddMaterielBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddMaterielFragment : Fragment() {

    private val viewModel: AddMaterielViewModel by viewModels()
    private lateinit var dataBinding: FragmentAddMaterielBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        dataBinding = FragmentAddMaterielBinding.inflate(layoutInflater, container, false)
        dataBinding.lifecycleOwner = viewLifecycleOwner
        dataBinding.viewModel = viewModel
        return dataBinding.root
    }
}