package net.prunusmume.ikastage.di

import dagger.Component
import net.prunusmume.ikastage.ui.activity.MainActivity
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(activity: MainActivity)
}
