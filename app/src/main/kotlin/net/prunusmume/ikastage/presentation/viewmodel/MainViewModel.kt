package net.prunusmume.ikastage.presentation.viewmodel

import net.prunusmume.ikastage.IkaStageApplication
import net.prunusmume.ikastage.entity.Schedule
import net.prunusmume.ikastage.network.IkaStageService
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.Subscriptions
import javax.inject.Inject

/**
 * Created by umetsu_kentaro on 16/01/28.
 */
class MainViewModel(private val listener: MainViewModel.Listener) : ViewModel {

    interface Listener {
        fun onSchedulesChanged(schedules: MutableList<Schedule>)
    }

    @Inject
    lateinit var ikaStageService: IkaStageService

    private var subscription: Subscription = Subscriptions.empty()

    init {
        IkaStageApplication.appComponent.inject(this)

        subscription = ikaStageService.schedules()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { schedules ->
                            listener.onSchedulesChanged(schedules)
                        },
                        { error ->
                        })
    }

    override fun destroy() {
        subscription.unsubscribe()
    }
}