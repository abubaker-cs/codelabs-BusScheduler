package com.example.busschedule

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.busschedule.database.schedule.Schedule
import com.example.busschedule.databinding.BusStopItemBinding
import java.text.SimpleDateFormat
import java.util.*

class BusStopAdapter(private val onItemClicked: (Schedule) -> Unit) :
    ListAdapter<Schedule, BusStopAdapter.BusStopViewHolder>(DiffCallback) {

    /**
     * ViewHolder()
     */
    class BusStopViewHolder(private var binding: BusStopItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SimpleDateFormat")
        fun bind(schedule: Schedule) {

            // Stop Name
            binding.stopNameTextView.text = schedule.stopName

            // Arrival Time (Date)
            binding.arrivalTimeTextView.text = SimpleDateFormat(
                "h:mm a"
            ).format(
                Date(schedule.arrivalTime.toLong() * 1000)
            )
        }

    }

    /**]
     * onCreate
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusStopViewHolder {

        // Inflate XML Layout
        val viewHolder = BusStopViewHolder(
            BusStopItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        // OnClick > Get the Current Position of the Clicked Item
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            onItemClicked(getItem(position))
        }

        //
        return viewHolder
    }

    /**
     * onBind
     */
    override fun onBindViewHolder(holder: BusStopViewHolder, position: Int) {

        // Current Position
        holder.bind(getItem(position))

    }

    /**
     * Companion
     *
     *
     */
    companion object {

        // DiffCallback
        private val DiffCallback = object : DiffUtil.ItemCallback<Schedule>() {

            // Same Items?
            override fun areItemsTheSame(oldItem: Schedule, newItem: Schedule): Boolean {
                return oldItem.id == newItem.id
            }

            // Same Content?
            override fun areContentsTheSame(oldItem: Schedule, newItem: Schedule): Boolean {
                return oldItem == newItem
            }

        }

    }
}