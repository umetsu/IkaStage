package net.prunusmume.ikastage.di

import dagger.Component
import net.prunusmume.ikastage.IkaStageApplication
import net.prunusmume.ikastage.presentation.view.activity.MainActivity
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(app: IkaStageApplication)
    fun inject(activity: MainActivity)
}
