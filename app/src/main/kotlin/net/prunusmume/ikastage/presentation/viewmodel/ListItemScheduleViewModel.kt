package net.prunusmume.ikastage.presentation.viewmodel

import net.prunusmume.ikastage.entity.Match
import net.prunusmume.ikastage.entity.Schedule

/**
 * Created by umetsu_kentaro on 16/01/28.
 */
class ListItemScheduleViewModel(var schedule: Schedule) : ViewModel {

    val time: String
        get() = "${schedule.startTime} ~ ${schedule.endTime}"

    val regularBattle: String
        get() = regular.type

    val regularFirstStageName: String
        get() = regular.stages[0].name

    val regularFirstStageImageUrl: String
        get() = regular.stages[0].imageUrl

    val regularSecondStageName: String
        get() = regular.stages[1].name

    val regularSecondStageImageUrl: String
        get() = regular.stages[1].imageUrl

    val rankedBattle: String
        get() = ranked.type

    val rankedBattleMode: String
        get() = ranked.rule ?: ""

    val rankedFirstStageName: String
        get() = ranked.stages[0].name

    val rankedFirstStageImageUrl: String
        get() = ranked.stages[0].imageUrl

    val rankedSecondStageName: String
        get() = ranked.stages[1].name

    val rankedSecondStageImageUrl: String
        get() = ranked.stages[1].imageUrl

    private val regular: Match
        get() = schedule.matchs[0]

    private val ranked: Match
        get() = schedule.matchs[1]

    override fun destroy() {
    }
}