package com.deliverroo.domain.util.ocr

import android.content.Context
import android.graphics.Bitmap
import com.googlecode.tesseract.android.TessBaseAPI
import com.googlecode.tesseract.android.TessBaseAPI.PageSegMode.PSM_SINGLE_COLUMN
import java.io.ByteArrayOutputStream

class OcrRunnable(
    context: Context,
    bitmap: Bitmap
): Runnable {
    private var tess: TessBaseAPI? = null
    private val context = context
    private var mThreadRunning = true
    private var mTessReady = false
    //private var image = image
    //private var mOcrParams: OcrParams? = null
    var result: String = ""
    var bitmap = bitmap

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
        //PSM 3 is the default behavior
        tess.pageSegMode = PSM_SINGLE_COLUMN
        tess.setVariable("tessedit_write_images","true") //shows how tesseract processing the image
        tess.setVariable("OEM_TESSERACT_LSTM_COMBINED","VAR_TRUE")
        //tess.setVariable("tessedit_pageseg_mode","PSM_SINGLE_COLUMN") //"tessedit_pageseg_mode" or TessBaseAPI.PageSegMode.
        //tess.setVariable("tessedit_char_whitelist", "0123456789") // find digits only
        tess.setImage(bitmap)
        result = tess.utF8Text
        tess.recycle()
    }

    fun changeBitmap(bitmapArg: Bitmap) {
        bitmap = bitmapArg
    }
    companion object
    {

        private val TAG = OcrRunnable::class.java.name
    }
}