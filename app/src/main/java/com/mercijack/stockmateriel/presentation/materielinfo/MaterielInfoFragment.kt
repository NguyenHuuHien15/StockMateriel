package com.mercijack.stockmateriel.presentation.materielinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.mercijack.stockmateriel.databinding.FragmentMaterielInfoBinding
import com.mercijack.stockmateriel.presentation.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MaterielInfoFragment : Fragment() {
    val LOG_TAG = MaterielInfoFragment::class.simpleName

    private lateinit var dataBinding: FragmentMaterielInfoBinding

    private val viewModel: MaterielInfoViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        dataBinding = FragmentMaterielInfoBinding.inflate(layoutInflater, container, false)
        dataBinding.lifecycleOwner = viewLifecycleOwner

        dataBinding.viewModelItem = viewModel
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.title = "Info du matériel"

        mainViewModel.setFullScreen(true)
        mainViewModel.setOnHomeFragment(false)

        viewModel.setMaterielCode(MaterielInfoFragmentArgs.fromBundle(requireArguments()).code)

        viewModel.backToPrevious.observe(viewLifecycleOwner, {
            if (it == true) {
                activity?.onBackPressed()
                viewModel.doneBackToPrevious()
            }
        })

        viewModel.removeSuccess.observe(viewLifecycleOwner, {
            if (it == true) Toast.makeText(context, "Matériel retiré en succès", Toast.LENGTH_LONG).show()
        })
    }

}