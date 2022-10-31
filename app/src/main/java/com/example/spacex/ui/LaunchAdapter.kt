package com.example.spacex.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.spacex.data.LaunchResponseItem
import com.example.spacex.databinding.ListRowBinding

class LaunchAdapter(
    private val data: MutableList<LaunchResponseItem> = mutableListOf(),
    private val onItemClicked: (LaunchResponseItem) -> Unit
    ) : RecyclerView.Adapter<LaunchAdapter.ViewHolder>() {

    private val KEY_LAUNCH = ""

    fun updateData(newData: List<LaunchResponseItem>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ListRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(data[position], onItemClicked)

    inner class ViewHolder(
        private val binding: ListRowBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: LaunchResponseItem, onClickedItem: (LaunchResponseItem) -> Unit) {

            binding.missionName.text = data.missionName

            itemView.setOnClickListener {
                onClickedItem(data)
            }
        }
    }
}