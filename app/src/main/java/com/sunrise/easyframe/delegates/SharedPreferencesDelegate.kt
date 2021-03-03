package com.sunrise.easyframe.delegates

import android.content.Context
import android.content.SharedPreferences
import java.lang.RuntimeException
import kotlin.reflect.KProperty

/**
 *@author: JiangYu
 *Date: 2021/3/3
 *Time: 9:14
 *Email: e1175132893@outlook.com
 */
class SharedPreferencesDelegate(context: Context, filename: String) {
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(filename, Context.MODE_PRIVATE)
    private val edit = sharedPreferences.edit()

    operator fun setValue(thisRef: Any, property: KProperty<*>, value: String) {
        edit.putString(property.name, value).commit()
    }

    operator fun setValue(thisRef: Any, property: KProperty<*>, value: Int) {
        edit.putInt(property.name, value).commit()
    }

    operator fun setValue(thisRef: Any, property: KProperty<*>, value: Boolean) {
        edit.putBoolean(property.name, value).commit()
    }

    operator fun setValue(thisRef: Any, property: KProperty<*>, value: Float) {
        edit.putFloat(property.name, value).commit()
    }

    operator fun setValue(thisRef: Any, property: KProperty<*>, value: Long) {
        edit.putLong(property.name, value).commit()
    }

    operator fun setValue(thisRef: Any, property: KProperty<*>, value: MutableSet<String>) {
        edit.putStringSet(property.name, value).commit()
    }

    inline operator fun <reified T> getValue(thisRef: Any, property: KProperty<*>): T {
        when (T::class) {
            String::class ->
                return sharedPreferences.getString(property.name, "") as T
            Int::class ->
                return sharedPreferences.getInt(property.name, 0) as T
            Float::class ->
                return sharedPreferences.getFloat(property.name, 0F) as T
            Boolean::class ->
                return sharedPreferences.getBoolean(property.name, false) as T
            Long::class ->
                return sharedPreferences.getLong(property.name, 0L) as T
            MutableSet::class ->
                return sharedPreferences.getStringSet(
                    property.name,
                    mutableSetOf("")
                ) as T
            else ->
                throw RuntimeException("No such sharedPreference or value!")
        }
    }
}

abstract class AbsSharedPreference(context: Context, filename: String) {
    protected val sharedPreference = SharedPreferencesDelegate(context, filename)
}