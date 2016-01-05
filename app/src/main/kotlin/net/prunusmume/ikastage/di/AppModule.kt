package net.prunusmume.ikastage.di

import android.app.Application
import dagger.Module
import dagger.Provides
import net.prunusmume.ikastage.IkaStageApplication
import javax.inject.Singleton


@Module
class AppModule(private val mApplication: IkaStageApplication) {

    @Provides
    @Singleton
    fun provideApplication(): Application {
        return mApplication
    }
}
