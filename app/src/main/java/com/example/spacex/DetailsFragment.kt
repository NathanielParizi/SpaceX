package com.example.spacex

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.spacex.databinding.FragmentDetailsBinding
import com.example.spacex.ui.LaunchesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private val viewModel: LaunchesViewModel by lazy {
        ViewModelProvider(requireActivity())[LaunchesViewModel::class.java]
    }

    private val binding by lazy {
        FragmentDetailsBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel.launchSelected?.let {
            // todo populate the UI
            binding.missionName.text = "Name:\t ${it.missionName}"
            binding.rocketName.text = "Rocket:\t${it.rocket.rocketName}"
            binding.launchSiteName.text = "Site Name:\t${it.launchSite.siteName}"
            binding.dateOfLaunch.text = "Launch Date:\t${it.lastLlLaunchDate}"
            binding.launchPatchName.text = "Mission Patch:\t${it.links.missionPatch}"
            Glide.with(this).load(it.links.missionPatch).into(binding.patchImg);

        } ?: run {
            AlertDialog.Builder(requireActivity())
                .setTitle("Error occurred")
                .setMessage("No Launch selected")
                .setNegativeButton("Dismiss") { dialog, _ ->
                    dialog.dismiss()
                }
        }
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(): DetailsFragment = DetailsFragment()
    }
}