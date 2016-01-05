package net.prunusmume.ikastage.entity

/**
 * Created by umetsu_kentaro on 16/01/05.
 */
data class Schedule(
        var startTime: String,
        var endTime: String,
        var matchs: MutableList<Match>
)