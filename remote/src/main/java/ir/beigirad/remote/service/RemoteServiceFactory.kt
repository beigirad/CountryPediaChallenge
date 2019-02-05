package ir.beigirad.remote.service

import android.content.Context
import com.readystatesoftware.chuck.ChuckInterceptor
import ir.beigiead.remote.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RemoteServiceFactory {

    fun provideService(context: Context, isDebugMode: Boolean): RemoteService {
        val interceptor = makeChuckInterceptor(context, isDebugMode)
        val okHttpClient = makeOkHttpClient(interceptor, isDebugMode)
        return makeRetrofit(okHttpClient)
    }

    private fun makeRetrofit(okHttpClient: OkHttpClient): RemoteService {
        val builder = Retrofit.Builder()
            .baseUrl(BuildConfig.Host)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        return builder.create(RemoteService::class.java)
    }

    private fun makeOkHttpClient(interceptor: ChuckInterceptor, isDebugMode: Boolean): OkHttpClient {
        val builder = OkHttpClient.Builder()

        if (isDebugMode)
            builder.addInterceptor(interceptor)

        return builder.build()
    }

    private fun makeChuckInterceptor(context: Context, isDebugMode: Boolean): ChuckInterceptor {
        return ChuckInterceptor(context)
            .showNotification(isDebugMode)
    }
}