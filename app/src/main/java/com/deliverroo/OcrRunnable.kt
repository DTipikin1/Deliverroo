package com.deliverroo

import android.content.Context
import android.graphics.Bitmap
import android.media.Image
import android.util.Log
import com.googlecode.tesseract.android.TessBaseAPI
import java.io.ByteArrayOutputStream
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

    private lateinit var selectedImageBitMap: Bitmap
    private lateinit var imageInBitMapAfterResize: Bitmap
    private val baos = ByteArrayOutputStream()
    val PREFERRED_IMAGE_SIZE = 400  //400kb
    val ONE_MB_TO_KB = 1024

    override fun run() {
        val tess = TessBaseAPI()
        if (!tess.init(context.filesDir.absolutePath, "heb")) {
            // Error initializing Tesseract (wrong data path or language)
            tess.recycle();
            return;
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