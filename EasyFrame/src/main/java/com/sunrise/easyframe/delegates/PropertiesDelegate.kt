package com.sunrise.easyframe.delegates

import android.content.Context
import java.util.*
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 *@author: Sunrise
 *Date: 2021/3/2
 *Time: 23:26
 *Email: e1175132893@outlook.com
 */
/**
 * 属性代理
 */
class PropertiesDelegate(
    private val context: Context,
    private val fileName: String,
    private val defaultValue: String = ""
) :
    ReadOnlyProperty<Any, String> {
    private val properties by lazy {
        val prop = Properties()
        try {
            context.assets.open(fileName).use {
                prop.load(it)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        prop
    }

    override fun getValue(thisRef: Any, property: KProperty<*>): String {
        return properties.getProperty(property.name, defaultValue)
    }
}

/**
 * 使用属性代理的类应该继承该抽象类
 * @param context Android上下文
 * @param fileName 属性文件全名
 */
abstract class AbsProperties(context: Context, fileName: String) {
    val prop = PropertiesDelegate(context, fileName)
}