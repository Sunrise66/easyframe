package com.sunrise.easyframe.delegates

import android.content.Context
import java.util.*
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 *@author: JiangYu
 *Date: 2021/3/2
 *Time: 23:26
 *Email: jiangyu@haogroup.com
 */
class PropertiesDelegate(
    private val context: Context,
    private val fileName: String,
    private val defaultValue: String = ""
) :
    ReadOnlyProperty<Any, String> {
    private val properties by lazy {
        val prop = Properties()
        context.assets.open(fileName).use {
            prop.load(it)
        }
        prop
    }

    override fun getValue(thisRef: Any, property: KProperty<*>): String {
        return properties.getProperty(property.name, defaultValue)
    }
}

abstract class AbsProperties(context: Context, fileName: String) {
    val prop = PropertiesDelegate(context, fileName)
}