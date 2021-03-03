package com.sunrise.easyframe.utils

import android.content.Context
import android.net.Uri
import android.os.Build
import androidx.core.content.FileProvider
import java.io.File

/**
 *@author: Sunrise
 *Date: 2021/3/3
 *Time: 9:35
 *Email: e1175132893@outlook.com
 */
object FileUriUtils {
    object FileUtils {
        fun getUri(context: Context, authorites: String, file: File): Uri {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                //设置7.0以上共享文件，分享路径定义在xml/file_paths.xml
                FileProvider.getUriForFile(context, authorites, file)
            } else {
                // 7.0以下,共享文件
                Uri.fromFile(file)
            }
        }
    }
}