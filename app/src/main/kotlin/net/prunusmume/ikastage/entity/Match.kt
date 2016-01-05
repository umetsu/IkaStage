package net.prunusmume.ikastage.entity

/**
 * Created by umetsu_kentaro on 16/01/05.
 */
data class Match(
        var type: String,
        var rule: String?,
        var stages: MutableList<Stage>
)