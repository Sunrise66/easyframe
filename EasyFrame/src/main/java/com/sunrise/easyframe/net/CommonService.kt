package com.sunrise.easyframe.net

import com.sunrise.easyframe.common.NetConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 *@author: Sunrise
 *Date: 2021/3/3
 *Time: 13:59
 *Email: e1175132893@outlook.com
 */
/**
 * 对Retrofit配置的简单封装
 */
object CommonService {

    private val retrofitDefault by lazy {
        Retrofit.Builder().baseUrl(NetConfig.getInstance().PRIMARY_SERVER_ADDRESS)
            .client(OkHttpClient().newBuilder().apply {
                addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            }.build())
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    fun getDefaultRetrofit(): Retrofit {
        return retrofitDefault
    }

    /**
     * 会由默认Retrofit配置执行创建
     */
    fun <T : Any> createApi(apiClass: Class<T>): T {
        return retrofitDefault.create(apiClass)
    }

    /**
     * Kotlin调用该方法存在默认OkHttpClient，请注意
     * @param baseUrl
     * @param okHttpClient
     */
    fun getCustomRetrofit(
        baseUrl: String,
        okHttpClient: OkHttpClient = OkHttpClient().newBuilder().apply {
            addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }.build()
    ): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    /**
     * 读取[NetConfig]
     */
    fun getCustomRetrofit(
        baseUrl: String,
        vararg interceptors: Interceptor
    ): Retrofit {
        val client = OkHttpClient.Builder().apply {
            connectTimeout(NetConfig.getInstance().CONNECT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            readTimeout(NetConfig.getInstance().READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
            writeTimeout(NetConfig.getInstance().WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
            callTimeout(NetConfig.getInstance().CALL_TIMEOUT.toLong(), TimeUnit.SECONDS)
            for (interceptor in interceptors) {
                addInterceptor(interceptor)
            }
        }.build()
        return Retrofit.Builder().baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
}