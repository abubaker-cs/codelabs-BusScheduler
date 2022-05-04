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

/**
 * The class extends a generic ListAdapter that takes:
 *      1. a list of Schedule objects and
 *      2. a BusStopViewHolder class for the UI
 *
 * - You also pass in a DiffCallback type
 * - The BusStopAdapter class itself also takes a parameter, onItemClicked(). This function will be
 *   used to handle navigation when an item is selected on the first screen, but for the second screen,
 *   you'll just pass in an empty function.
 */
class BusStopAdapter(private val onItemClicked: (Schedule) -> Unit) :
    ListAdapter<Schedule, BusStopAdapter.BusStopViewHolder>(DiffCallback) {

    /**
     * ViewHolder() It will allow us to access views created from your layout file in code.
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
     * onCreate - inflate xml layout
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
        // set the onClickListener() to call onItemClicked() for the item at the current position.
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            onItemClicked(getItem(position))
        }

        //
        return viewHolder
    }

    /**
     * onBind - to bind the view at the specified position.
     */
    override fun onBindViewHolder(holder: BusStopViewHolder, position: Int) {

        // Current Position
        holder.bind(getItem(position))

    }

    /**
     * Companion - DiffCallback
     *
     * This is just an object that helps the ListAdapter determine which items in the new and
     * old lists are different when updating the list.
     */
    companion object {

        // DiffCallback
        private val DiffCallback = object : DiffUtil.ItemCallback<Schedule>() {

            /**
             * These methods allow the ListAdapter to determine which items have been inserted,
             * updated, and deleted so that the UI can be updated accordingly.
             */

            // 1. Same Items? - checks if the object/row is the same by only checking the ID
            override fun areItemsTheSame(oldItem: Schedule, newItem: Schedule): Boolean {
                return oldItem.id == newItem.id
            }

            // 2. Same Content? - checks if all properties, not just the ID, are the same.
            override fun areContentsTheSame(oldItem: Schedule, newItem: Schedule): Boolean {
                return oldItem == newItem
            }

        }

    }
}