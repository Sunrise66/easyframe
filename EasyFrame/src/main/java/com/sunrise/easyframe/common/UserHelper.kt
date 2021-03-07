package com.sunrise.easyframe.common

import android.content.Context
import com.sunrise.easyframe.delegates.AbsSharedPreference

/**
 *@author: JiangYu
 *Date: 2021/3/5
 *Time: 20:23
 *Email: jiangyu@haogroup.com
 */
/**
 * 用户配置，不满足需求可自定义
 */
class UserHelper(context: Context) : AbsSharedPreference(context, "user_info") {
    var userJson: String by sharedPreference
    var token: String by sharedPreference
    var extras: MutableSet<String> by sharedPreference

    companion object {
        private lateinit var instance: UserHelper

        @JvmStatic
        fun init(context: Context) {
            synchronized(UserHelper::class.java) {
                instance = UserHelper(context)
            }
        }

        @JvmStatic
        fun getInstance(): UserHelper {
            return instance
        }
    }
}