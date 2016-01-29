package net.prunusmume.ikastage.presentation.view.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import com.trello.rxlifecycle.components.support.RxAppCompatActivity
import net.prunusmume.ikastage.R
import net.prunusmume.ikastage.databinding.ActivityMainBinding
import net.prunusmume.ikastage.entity.Schedule
import net.prunusmume.ikastage.presentation.view.adapter.ScheduleListAdapter
import net.prunusmume.ikastage.presentation.viewmodel.MainViewModel


class MainActivity : RxAppCompatActivity(), MainViewModel.Listener {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        viewModel = MainViewModel(this)
        binding.viewModel = viewModel

        setUpRecyclerView(binding.recyclerView)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.destroy()
    }

    override fun onSchedulesChanged(schedules: MutableList<Schedule>) {
        val adapter = binding.recyclerView.adapter as ScheduleListAdapter
        adapter.addAll(schedules)
        adapter.notifyDataSetChanged()
    }

    private fun setUpRecyclerView(recyclerView: RecyclerView) {
        val adapter = ScheduleListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
    }
}
