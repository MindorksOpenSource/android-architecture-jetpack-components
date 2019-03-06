package com.mindorks.framework.jetpack.utils

import android.content.Context
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