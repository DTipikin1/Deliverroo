package com.deliverroo

import com.googlecode.tesseract.android.TessBaseAPI

class OcrRunnable(): Runnable {
    private var tess: TessBaseAPI? = null
    override fun run() {
        tess = TessBaseAPI()
    }
}