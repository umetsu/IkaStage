package net.prunusmume.ikastage.presentation.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import net.prunusmume.ikastage.databinding.ListItemScheduleBinding
import net.prunusmume.ikastage.entity.Schedule
import net.prunusmume.ikastage.presentation.viewmodel.ListItemScheduleViewModel
import java.util.*

/**
 * Created by umetsu_kentaro on 16/01/14.
 */
class ScheduleListAdapter(private val context: Context) : RecyclerView.Adapter<ScheduleListAdapter.ViewHolder>() {

    private val schedules = ArrayList<Schedule>()
    private val layoutInflater: LayoutInflater

    init {
        layoutInflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(ListItemScheduleBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindSchedule(schedules[position])
    }

    override fun getItemCount(): Int {
        return schedules.size
    }

    fun addAll(list: MutableList<Schedule>) {
        schedules.addAll(list)
        notifyDataSetChanged()
    }

    fun clear() {
        schedules.clear()
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ListItemScheduleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindSchedule(schedule: Schedule) {
            if (binding.viewModel == null) {
                binding.viewModel = ListItemScheduleViewModel(schedule)
            } else {
                binding.viewModel.schedule = schedule
            }
        }
    }
}


