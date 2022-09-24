package com.deliverroo

import android.content.Context
import android.media.Image
import android.util.Log
import com.googlecode.tesseract.android.TessBaseAPI
import java.io.File

class OcrRunnable(
    context: Context,
    file: File
): Runnable {
    private var tess: TessBaseAPI? = null
    private val context = context
    private var mThreadRunning = true
    private var mTessReady = false
    //private var image = image
    private var file = file
    //private var mOcrParams: OcrParams? = null
    var result: String = ""

    override fun run() {
        val tess = TessBaseAPI()
        if (!tess.init(context.filesDir.absolutePath, "heb")) {
            // Error initializing Tesseract (wrong data path or language)
            tess.recycle();
            return;
        }
        if (file == null) {
            Log.d("test","NULL")
        }
        tess.setImage(file)
        result = tess.utF8Text
        Log.d(TAG,result)
    }
    companion object
    {

        private val TAG = OcrRunnable::class.java.name
    }
}