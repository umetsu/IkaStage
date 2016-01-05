package net.prunusmume.ikastage.di

import android.app.Application
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.logging.HttpLoggingInterceptor
import dagger.Module
import dagger.Provides
import net.prunusmume.ikastage.IkaStageApplication
import net.prunusmume.ikastage.network.IkaStageService
import retrofit.GsonConverterFactory
import retrofit.Retrofit
import retrofit.RxJavaCallAdapterFactory
import javax.inject.Singleton


@Module
class AppModule(private val mApplication: IkaStageApplication) {

    @Provides
    @Singleton
    fun provideApplication(): Application {
        return mApplication
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
                .create()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        // log
        val logger = HttpLoggingInterceptor()
        logger.setLevel(HttpLoggingInterceptor.Level.BASIC)

        val client = OkHttpClient()
        client.interceptors().add(logger)

        return client
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
                .baseUrl("http://ec2-52-193-37-73.ap-northeast-1.compute.amazonaws.com")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
    }

    @Provides
    @Singleton
    fun provideIkaStageService(retrofit: Retrofit): IkaStageService {
        return retrofit.create(IkaStageService::class.java)
    }
}
