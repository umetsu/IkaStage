package net.prunusmume.ikastage

import android.app.Application
import com.bumptech.glide.Glide
import com.bumptech.glide.integration.okhttp.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import com.squareup.okhttp.OkHttpClient
import net.prunusmume.ikastage.di.AppComponent
import net.prunusmume.ikastage.di.AppModule
import net.prunusmume.ikastage.di.DaggerAppComponent
import java.io.InputStream
import javax.inject.Inject


class IkaStageApplication : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    @Inject
    lateinit var mOkHttpClient: OkHttpClient

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
        appComponent.inject(this)

        Glide.get(this).register(GlideUrl::class.java, InputStream::class.java,
                OkHttpUrlLoader.Factory(mOkHttpClient))
    }
}