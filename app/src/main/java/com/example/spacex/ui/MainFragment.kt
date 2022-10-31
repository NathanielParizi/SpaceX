package com.example.spacex.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spacex.DetailsFragment
import com.example.spacex.MainActivity
import com.example.spacex.R
import com.example.spacex.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val binding by lazy {
        FragmentMainBinding.inflate(layoutInflater)
    }

    private val viewModel: LaunchesViewModel by lazy {
        ViewModelProvider(requireActivity())[LaunchesViewModel::class.java]
    }

    private val launchAdapter by lazy {
        LaunchAdapter {
            viewModel.launchSelected = it

            val fragment = DetailsFragment.newInstance()
            parentFragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .replace(R.id.container, fragment, "detail")
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter =  launchAdapter
        }

        setupObserver()

        return binding.root
    }

    private fun setupObserver() {
        viewModel.launch.observe(viewLifecycleOwner) {
            Log.d("Main", "$it")
            launchAdapter.updateData(it)
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}