package net.prunusmume.ikastage.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import net.prunusmume.ikastage.databinding.ListItemScheduleBinding
import net.prunusmume.ikastage.entity.Schedule
import java.util.*

/**
 * Created by umetsu_kentaro on 16/01/14.
 */
class ScheduleListAdapter(private val mContext: Context) : RecyclerView.Adapter<ScheduleListAdapter.ViewHolder>() {

    private val mSchedules = ArrayList<Schedule>()
    private val mLayoutInflater: LayoutInflater

    init {
        mLayoutInflater = LayoutInflater.from(mContext)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(ListItemScheduleBinding.inflate(mLayoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mSchedules[position]
        val binding = holder.binding

        // time
        binding.timeText.text = "${item.startTime} ~ ${item.endTime}"

        // レギュラーマッチ
        val regular = item.matchs[0]
        binding.regularBattleText.text = regular.type

        Glide.with(mContext).load(regular.stages[0].imageUrl).into(binding.regularStageImage1)
        binding.regularStageNameText1.text = regular.stages[0].name

        Glide.with(mContext).load(regular.stages[1].imageUrl).into(binding.regularStageImage2)
        binding.regularStageNameText2.text = regular.stages[1].name

        // ガチマッチ
        val ranked = item.matchs[1]
        binding.rankedBattleText.text = ranked.type
        binding.rankedBattleModeText.text = ranked.rule

        Glide.with(mContext).load(ranked.stages[0].imageUrl).into(binding.rankedStageImage1)
        binding.rankedStageNameText1.text = ranked.stages[0].name

        Glide.with(mContext).load(ranked.stages[1].imageUrl).into(binding.rankedStageImage2)
        binding.rankedStageNameText2.text = ranked.stages[1].name
    }

    override fun getItemCount(): Int {
        return mSchedules.size
    }

    fun addAll(list: MutableList<Schedule>) {
        mSchedules.addAll(list)
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ListItemScheduleBinding) : RecyclerView.ViewHolder(binding.root) {
    }
}


