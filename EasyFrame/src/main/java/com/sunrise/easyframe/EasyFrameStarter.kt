package com.sunrise.easyframe

import android.content.Context
import com.sunrise.easyframe.common.NetConfig
import com.sunrise.easyframe.common.UserHelper

/**
 *@author: JiangYu
 *Date: 2021/3/5
 *Time: 20:19
 *Email: jiangyu@haogroup.com
 */
/**
 * EasyFrame一键启动器
 */
object EasyFrameStarter {
    fun init(context: Context) {
        NetConfig.init(context)
        UserHelper.init(context)
    }
}