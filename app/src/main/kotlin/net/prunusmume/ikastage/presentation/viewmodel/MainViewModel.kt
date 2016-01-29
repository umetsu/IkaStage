package net.prunusmume.ikastage.presentation.viewmodel

import android.databinding.ObservableBoolean
import android.databinding.ObservableInt
import android.support.v4.widget.SwipeRefreshLayout
import android.view.View
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
        fun onClearSchedules()
        fun onSchedulesChanged(schedules: MutableList<Schedule>)
    }

    @Inject
    lateinit var ikaStageService: IkaStageService

    private var subscription: Subscription = Subscriptions.empty()

    val recyclerViewVisibility: ObservableInt = ObservableInt(View.VISIBLE)

    val emptyViewVisibility: ObservableInt = ObservableInt(View.GONE)

    val refreshing: ObservableBoolean = ObservableBoolean(false)

    val onRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        loadSchedules()
    }

    init {
        IkaStageApplication.appComponent.inject(this)
        loadSchedules()
    }

    override fun destroy() {
        subscription.unsubscribe()
    }

    private fun loadSchedules() {
        refreshing.set(true)
        recyclerViewVisibility.set(View.INVISIBLE)
        emptyViewVisibility.set(View.INVISIBLE)

        subscription = ikaStageService.schedules()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { schedules ->
                            refreshing.set(false)
                            recyclerViewVisibility.set(View.VISIBLE)
                            emptyViewVisibility.set(View.GONE)

                            listener.onClearSchedules()
                            listener.onSchedulesChanged(schedules)
                        },
                        { error ->
                            refreshing.set(false)
                            // SwipeRefreshLayoutのプログレスを表示するためにVISIBLEにしておく
                            recyclerViewVisibility.set(View.VISIBLE)
                            emptyViewVisibility.set(View.VISIBLE)

                            listener.onClearSchedules()
                        })
    }
}