package com.mindorks.framework.jetpack.utils

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager
import java.io.IOException
import java.nio.charset.Charset

/**
 * Created by jyotidubey on 2019-03-05.
 */

@Throws(IOException::class)
fun loadJSONFromAsset(context: Context, jsonFileName: String): String {
    val manager = context.assets
    val inputStream = manager.open(jsonFileName)

    val size = inputStream.available()
    val buffer = ByteArray(size)
    inputStream.read(buffer)
    inputStream.close()

    return String(buffer, Charset.forName("UTF-8"))
}

fun getScreenHeight(context: Context?): Int {
    val windowManager = context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val dm = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(dm)
    return dm.heightPixels
}

fun getScreenWidth(context: Context?): Int {
    val windowManager = context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val dm = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(dm)
    return dm.widthPixels
}
