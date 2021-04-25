package com.mercijack.stockmateriel.presentation.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.mercijack.stockmateriel.databinding.FragmentNotificationsBinding
import com.mercijack.stockmateriel.presentation.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationsFragment : Fragment() {

    private lateinit var dataBinding: FragmentNotificationsBinding

    private val viewModel: NotificationsViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = FragmentNotificationsBinding.inflate(layoutInflater, container, false)
        dataBinding.lifecycleOwner = viewLifecycleOwner

        dataBinding.viewModel = viewModel
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.title = "Notifications"

        mainViewModel.setFullScreen(false)
    }
}