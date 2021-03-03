package com.sunrise.easyframe.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import java.io.UnsupportedEncodingException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

/**
 *@author: Sunrise
 *Date: 2021/3/3
 *Time: 10:04
 *Email: e1175132893@outlook.com
 */

fun String.md5(): String {
    lateinit var hash: ByteArray
    try {
        hash = MessageDigest.getInstance("MD5").digest(this.toByteArray())
    } catch (var7: NoSuchAlgorithmException) {
        throw RuntimeException("Huh, MD5 should be supported?", var7)
    } catch (var8: UnsupportedEncodingException) {
        throw RuntimeException("Huh, UTF-8 should be supported?", var8)
    }
    val hex = StringBuilder(hash.size * 2)
    for (b in hash) {
        var i: Int = b.toInt() and 0xff//获取低八位有效值
        var hexString = Integer.toHexString(i)//将整数转化为16进制
        if (hexString.length < 2) {
            hexString = "0$hexString"//如果是一位的话，补0
        }
        hex.append(hexString)
    }
    return hex.toString()
}

fun Context.copyToClipboard(copyStr: String): Boolean {
    return try {
        val cm = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val mClipData = ClipData.newPlainText("Label", copyStr)
        cm.setPrimaryClip(mClipData)
        true
    } catch (e: Exception) {
        e.printStackTrace()
        false
    }
}

fun Context.getVersionCode(): String {
    return try {
        this.packageManager.getPackageInfo(this.packageName, 0).versionName
    } catch (e: java.lang.Exception) {
        "未知版本"
    }
}

