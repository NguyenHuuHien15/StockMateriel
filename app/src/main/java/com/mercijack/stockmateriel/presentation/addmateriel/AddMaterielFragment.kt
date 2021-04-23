package com.mercijack.stockmateriel.presentation.addmateriel

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.internal.TextWatcherAdapter
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = "Rajoute d'un matériel"

        dataBinding.tfCode.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // do nothing
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // do nothing
            }

            override fun afterTextChanged(s: Editable?) {
                viewModel.updateCode(s.toString())
            }

        })

        dataBinding.tfName.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // do nothing
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // do nothing
            }

            override fun afterTextChanged(s: Editable?) {
                viewModel.updateName(s.toString())
            }

        })

        viewModel.addSuccess.observe(viewLifecycleOwner, {
            if(it == true) Toast.makeText(context, "Matériel ajouté en succès", Toast.LENGTH_LONG).show()
        })
    }
}