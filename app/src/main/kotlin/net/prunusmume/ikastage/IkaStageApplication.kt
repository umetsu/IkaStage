package net.prunusmume.ikastage

import android.app.Application
import net.prunusmume.ikastage.di.AppComponent
import net.prunusmume.ikastage.di.AppModule
import net.prunusmume.ikastage.di.DaggerAppComponent


class IkaStageApplication : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }
}