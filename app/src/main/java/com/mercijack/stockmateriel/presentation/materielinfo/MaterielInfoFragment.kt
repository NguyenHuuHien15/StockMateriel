package com.mercijack.stockmateriel.presentation.materielinfo

import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mercijack.stockmateriel.MainActivity
import com.mercijack.stockmateriel.databinding.FragmentMaterielInfoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MaterielInfoFragment : Fragment() {
    val LOG_TAG = MaterielInfoFragment::class.simpleName

    private lateinit var dataBinding: FragmentMaterielInfoBinding

    private val viewModel: MaterielInfoViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        dataBinding = FragmentMaterielInfoBinding.inflate(layoutInflater, container, false)
        dataBinding.lifecycleOwner = viewLifecycleOwner
        dataBinding.viewModelItem = viewModel
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainActivity = activity as MainActivity
        mainActivity.supportActionBar?.hide()
        mainActivity.hideBottomNav()

        val window = mainActivity.window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window?.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window?.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }

        viewModel.setMaterielCode(MaterielInfoFragmentArgs.fromBundle(requireArguments()).code)

        viewModel.backToPrevious.observe(viewLifecycleOwner, {
            if (it == true) {
                activity?.onBackPressed()
                viewModel.doneBackToPrevious()
            }
        })
    }

}