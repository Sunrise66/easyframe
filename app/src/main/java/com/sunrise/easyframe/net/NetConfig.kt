package com.sunrise.easyframe.net

import android.app.Application
import android.content.Context
import com.sunrise.easyframe.delegates.AbsProperties

/**
 *@author: Sunrise
 *Date: 2021/3/3
 *Time: 9:39
 *Email: e1175132893@outlook.com
 */
/**
 * 网络设置，如需使用请在Application中进行执行init初始化
 * 该类只是简单示范，可自定义更加丰富的设置来符合业务需求
 */
class NetConfig(context: Context) : AbsProperties(context, "net_config.properties") {
    val PRIMARY_SERVER_ADDRESS by prop
    val SECONDARY_SERVER_ADDRESS by prop
    val CONNECT_TIMEOUT by prop
    val READ_TIMEOUT by prop
    val WRITE_TIMEOUT by prop
    val CALL_TIMEOUT by prop

    companion object {
        @Volatile
        private lateinit var instance: NetConfig

        /**
         * 初始化方法，需在Application中执行该方法
         */
        @JvmStatic
        fun init(application: Application) {
            synchronized(NetConfig::class.java) {
                instance = NetConfig(application)
            }
        }

        @JvmStatic
        fun getInstance(): NetConfig {
            return instance
        }
    }
}