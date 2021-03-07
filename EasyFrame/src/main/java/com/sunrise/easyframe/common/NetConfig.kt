package com.sunrise.easyframe.common

import android.content.Context
import com.sunrise.easyframe.delegates.AbsProperties

/**
 *@author: Sunrise
 *Date: 2021/3/3
 *Time: 9:39
 *Email: e1175132893@outlook.com
 */
/**
 * 网络设置
 *
 * 该类只是简单示范，可自定义更加丰富的设置来符合业务需求
 *
 * 配置详情在../assets/net_config.properties中，如果没有请自行创建，配置名请和字段保持一致
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
        fun init(context: Context) {
            synchronized(NetConfig::class.java) {
                instance =
                    NetConfig(context)
            }
        }

        @JvmStatic
        fun getInstance(): NetConfig {
            return instance
        }
    }
}