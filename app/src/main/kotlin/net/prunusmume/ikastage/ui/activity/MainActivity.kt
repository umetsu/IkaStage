package net.prunusmume.ikastage.ui.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import com.trello.rxlifecycle.components.support.RxAppCompatActivity
import net.prunusmume.ikastage.IkaStageApplication
import net.prunusmume.ikastage.R
import net.prunusmume.ikastage.databinding.ActivityMainBinding
import net.prunusmume.ikastage.entity.Schedule
import net.prunusmume.ikastage.network.IkaStageService
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject


class MainActivity : RxAppCompatActivity() {

    companion object {
        val TAG = MainActivity::class.java.simpleName
    }

    @Inject
    lateinit var mIkaStageService: IkaStageService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        IkaStageApplication.appComponent.inject(this)

        var binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.text.text = "Hello"

        loadSchedules()
    }

    private fun loadSchedules() {
        mIkaStageService.schedules()
                .compose(bindToLifecycle<MutableList<Schedule>>())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(createLoadSuccessListener(),
                        createLoadFailureListener())
    }

    private fun createLoadSuccessListener(): (MutableList<Schedule>) -> Unit {
        // onSuccess(MutableList<Schedule>) -> Unit みたいなメソッドを関数リファレンスで引数に渡そうとすると
        // うまくSAM変換できないっぽい？仕方ないので関数を作って返すことにする
        return { schedules ->
            Log.d(TAG, "$schedules")
        }
    }

    private fun createLoadFailureListener(): (Throwable) -> Unit {
        return { error ->
            Log.e(TAG, "$error")
        }
    }

}
