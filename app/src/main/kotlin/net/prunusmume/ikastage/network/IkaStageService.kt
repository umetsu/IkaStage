package net.prunusmume.ikastage.network

import net.prunusmume.ikastage.entity.Schedule
import retrofit.http.GET
import rx.Observable

/**
 * Created by umetsu_kentaro on 16/01/05.
 */
interface IkaStageService {

    @GET("/v1/schedules")
    fun schedules(): Observable<MutableList<Schedule>>
}