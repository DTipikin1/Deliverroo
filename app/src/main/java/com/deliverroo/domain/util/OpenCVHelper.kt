package com.deliverroo.domain.util

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import org.opencv.android.Utils
import org.opencv.core.*
import org.opencv.imgcodecs.Imgcodecs
import org.opencv.imgproc.Imgproc


class OpenCVHelper {
    companion object {
        private const val TAG = "OpenCV"
        fun makeGreyScaleToBitmap(bitmap: Bitmap) : Bitmap {
            // Create OpenCV mat object and copy content from bitmap
            val mat = Mat()
            Utils.bitmapToMat(bitmap, mat)

            // Convert to grayscale
            Imgproc.cvtColor(mat, mat, Imgproc.COLOR_RGB2GRAY)

            // Make a mutable bitmap to copy grayscale image
            val grayBitmap = bitmap.copy(bitmap.config, true)
            Utils.matToBitmap(mat, grayBitmap)

            return grayBitmap
        }
        /*
        Performs adaptive threshold on an jpeg
        @param filePath the path to the image
        @param methodParam which adaptive method to perform, 0 for mean, 1 for gaussian
         */
        fun AdaptiveThresh(filePath: String, methodParam: Int, maxValue: Double, blockSize: Int, constant: Double) : Unit{
            var adaptiveMethod: Int
            if (methodParam == 0) {
                adaptiveMethod = Imgproc.ADAPTIVE_THRESH_MEAN_C
            } else {
                adaptiveMethod = Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C
            }
            // Reading the image
            val src = Imgcodecs.imread(filePath, 0)

            // Creating an empty matrix to store the result
            val dst = Mat()

            Imgproc.adaptiveThreshold(
                src, dst, maxValue, adaptiveMethod,
                Imgproc.THRESH_BINARY, blockSize, constant
            )
            // Writing the image
            Imgcodecs.imwrite("sdcard/Download/ocrimages/at.jpg", dst)
        }
        fun AdaptiveThreshToBitmap(imageBitmap: Bitmap , adaptiveMethod: Int, maxValue: Double, blockSize: Int, constant: Double) : Bitmap{
            var bitmap: Bitmap = imageBitmap
            var _adaptiveMethod = adaptiveMethod
            if (_adaptiveMethod == 0) {
                _adaptiveMethod = Imgproc.ADAPTIVE_THRESH_MEAN_C
            } else {
                _adaptiveMethod = Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C
            }

            val src = Mat()
            Utils.bitmapToMat(bitmap, src)
            Imgproc.cvtColor(src, src, Imgproc.COLOR_RGB2GRAY) //turn mat to grey
            // Creating an empty matrix to store the result
            val dst = Mat(src.rows(), src.cols(), src.type())

            Imgproc.adaptiveThreshold(
                src, dst, maxValue, _adaptiveMethod,
                Imgproc.THRESH_BINARY, blockSize, constant
            )
            try {
                //Imgproc.cvtColor(seedsImage, tmp, Imgproc.COLOR_RGB2BGRA);
                bitmap = Bitmap.createBitmap(dst.cols(), dst.rows(), Bitmap.Config.ARGB_8888)
                Utils.matToBitmap(dst, bitmap)
            } catch (e: CvException) {
                Log.d(TAG, e.message!!)
            }
            return bitmap
        }
        /*
        Shrinking character edges to make them thinner
         */
        fun erode(filePath: String): Unit {
            val src = Imgcodecs.imread(filePath, 0)
            //Creating destination matrix
            val dst = Mat(src.rows(), src.cols(), src.type())
            //Preparing the kernel matrix object
            val kernel =
                Imgproc.getStructuringElement(Imgproc.MORPH_RECT, Size(2 * 2 + 1.0, 2 * 2 + 1.0))
            Imgproc.erode(src, dst, kernel)
            Imgcodecs.imwrite("sdcard/Download/ocrimages/ae.jpg", dst)
            Log.d(TAG,"erosion completed")
        }
        /*
        Growing character edges to make them thinner
         */
        fun dilate(filePath: String): Unit {
            val src = Imgcodecs.imread(filePath, 0)
            //Creating destination matrix
            val dst = Mat(src.rows(), src.cols(), src.type())
            //Preparing the kernel matrix object
            val kernel =
                Imgproc.getStructuringElement(Imgproc.MORPH_RECT, Size(2 * 2 + 1.0, 2 * 2 + 1.0))
            Imgproc.dilate(src, dst, kernel)
            Imgcodecs.imwrite("sdcard/Download/ocrimages/ad.jpg", dst)
            Log.d(TAG,"dilation completed")
        }
        /*
        Shrinking character edges to make them thinner
        @param imageBitmap the bitmap to erode
        @return bitmap eroded bitmap
         */
        fun erodeToBitmap(imageBitmap: Bitmap): Bitmap {
            var bitmap = imageBitmap
            val src = Mat()
            Utils.bitmapToMat(bitmap, src)
            //Creating destination matrix
            val dst = Mat(src.rows(), src.cols(), src.type())
            //Preparing the kernel matrix object
            val kernel =
                Imgproc.getStructuringElement(Imgproc.MORPH_RECT, Size(2 * 2 + 1.0, 2 * 2 + 1.0))
            Imgproc.erode(src, dst, kernel)
            Log.d(TAG,"erosion completed")
            try {
                //Imgproc.cvtColor(seedsImage, tmp, Imgproc.COLOR_RGB2BGRA);
                bitmap = Bitmap.createBitmap(dst.cols(), dst.rows(), Bitmap.Config.ARGB_8888)
                Utils.matToBitmap(dst, bitmap)
            } catch (e: CvException) {
                Log.d(TAG, e.message!!)
            }
            return bitmap
        }
        /*
        Growing character edges to make them thinner
        @param imageBitmap the bitmap to dilate
        @return bitmap dilated bitmap
         */
        fun dilateToBitmap(imageBitmap: Bitmap): Bitmap {
            var bitmap = imageBitmap
            val src = Mat()
            Utils.bitmapToMat(bitmap, src)
            //Creating destination matrix
            val dst = Mat(src.rows(), src.cols(), src.type())
            //Preparing the kernel matrix object
            val kernel =
                Imgproc.getStructuringElement(Imgproc.MORPH_RECT, Size(2 * 2 + 1.0, 2 * 2 + 1.0))
            Imgproc.dilate(src, dst, kernel)
            Log.d(TAG,"dilation completed")
            try {
                //Imgproc.cvtColor(seedsImage, tmp, Imgproc.COLOR_RGB2BGRA);
                bitmap = Bitmap.createBitmap(dst.cols(), dst.rows(), Bitmap.Config.ARGB_8888)
                Utils.matToBitmap(dst, bitmap)
            } catch (e: CvException) {
                Log.d(TAG, e.message!!)
            }
            return bitmap
        }
        /*
        Resizing an image file
         */
        fun resize(filePath: String, width: Double, height: Double) {
            val src = Imgcodecs.imread(filePath, 0)
            val dst = Mat()
            Imgproc.resize(
                src, dst, Size(width, height), 0.1, 0.1,
                Imgproc.INTER_AREA
            )
            Imgcodecs.imwrite("sdcard/Download/ocrimages/ar.jpg", dst);
            Log.d(TAG,"resizing completed")
        }
        /*
        Resizing an image file and return a bitmap
         */
        fun resizeToBitmap(imageBitmap: Bitmap, width: Double, height: Double): Bitmap {
            var bitmap = imageBitmap
            val src = Mat()
            Utils.bitmapToMat(bitmap, src)
            val dst = Mat()
            Imgproc.resize(
                src, dst, Size(width, height), 0.1, 0.1,
                Imgproc.INTER_AREA
            )
            try {
                //Imgproc.cvtColor(seedsImage, tmp, Imgproc.COLOR_RGB2BGRA);
                bitmap = Bitmap.createBitmap(dst.cols(), dst.rows(), Bitmap.Config.ARGB_8888)
                Utils.matToBitmap(dst, bitmap)
            } catch (e: CvException) {
                Log.d(TAG, e.message!!)
            }
            Log.d(TAG,"resizing completed")
            return bitmap
        }
        fun dynamicResize(filePath: String, fx: Double, fy: Double) {
            val src = Imgcodecs.imread(filePath, 0)
            val dst = Mat()
            Imgproc.resize(
                src, dst, Size(0.0,0.0), fx, fy,
                Imgproc.INTER_AREA
            )
            Imgcodecs.imwrite("sdcard/Download/ocrimages/ar.jpg", dst);
            Log.d(TAG,"resizing completed")
        }
        fun dynamicResizeToBitmap(imageBitmap: Bitmap, fx: Double, fy: Double): Bitmap {
            var bitmap = imageBitmap
            val src = Mat()
            Utils.bitmapToMat(bitmap, src)
            val dst = Mat()
            Imgproc.resize(
                src, dst, Size(0.0, 0.0), fx, fy,
                Imgproc.INTER_AREA
            )
            try {
                //Imgproc.cvtColor(seedsImage, tmp, Imgproc.COLOR_RGB2BGRA);
                bitmap = Bitmap.createBitmap(dst.cols(), dst.rows(), Bitmap.Config.ARGB_8888)
                Utils.matToBitmap(dst, bitmap)
            } catch (e: CvException) {
                Log.d(TAG, e.message!!)
            }
            Log.d(TAG,"resizing completed")
            return bitmap
        }
        /*
        Checks if a string is an Israeli phone number
        @param text a string to check if its an Israeli phone number
         */
        private fun validPhoneNumber(text: String): Boolean {
            var result = false
            //mobile phone numbers in israel can very between 9 and 10 digits
            if (text.length in 9..10) {
                for (digit in text.iterator()) {
                    if (!digit.isDigit()) {
                        return result
                    }
                }
                result = true
                return result
            } else {
                return result
            }
        }
        /*
        Detects if there is a valid Israeli phone number in the ocr result text
        @param ocrResult the ocr result
        @return returns true if there is a valid phone number
         */
        fun phoneNumberDetected(ocrResult: String): Boolean {
            var result = false
            val charGroups = ocrResult.split(" ").toTypedArray()
            //Phone number should be in the first 5 groups
            for (charGroup in charGroups) {
               if (validPhoneNumber(charGroup)) {
                   Log.d(TAG, "A VAILD PHONE NUMBER: $charGroup")
                   result = true
               }
            }
            return result
        }
        /*
        Gets the number of digits from the phone number in the ocr result
        @param ocrResult the result from the ocr
        @return the number of digits in the phone number
         */
        fun getPhoneDetectionAccuracy(ocrResult: String): Int {
            var currentDigits = 0
            var highestDigits = 0
            val charGroups = ocrResult.split(" ").toTypedArray()
            Log.d(TAG, charGroups.toString())
            //assumes the phone num is atleast in the first 5 strings of the result in the optimal ocr detection
                for (charGroup in charGroups) {
                    currentDigits = countDigitsInString(charGroup)
                    if (currentDigits > highestDigits) {
                        highestDigits = currentDigits
                    }
                }
            return highestDigits
        }
        /*
        Counts the number of digits in a string
        @param text the string to count in
        @return counter num of digits in the string
         */
        fun countDigitsInString(text: String): Int {
            var counter = 0
            for (char in text.iterator()) {
                if (char.isDigit()) {
                    counter++
                }
            }
            return counter
        }
        fun getOptimalImageThresholding(imageBitmap: Bitmap, context: Context): MutableList<Any> {
            /*
            AM I         maxV D       blockSize i      constant D
            0 to 1      0 to 255    0 to (11) 19    -20 to (12) 20
             */
            var adaptiveMethod = 0
            var maxValue = 0.0
            var blockSize = 0
            var constant = 0.0
            var highestPhoneDectectionAccuracy = 0
            var currentPhoneDetectionAccuracy = 0
            var phoneNumberOccurrences = 0
            var result: MutableList<Any> = mutableListOf() //am,maxV,BS,C,PhonenumOCCURs

            val ocr = OcrRunnable(context, imageBitmap)
            var ocrCounter = 0
            var ocrResult: String
            var bitmapPostThres: Bitmap

            for (blockSize1 in 1..20) {
                for (constant1 in 9..19) {
                    for (adaptiveMethod1 in 0..1) {
                        if (blockSize1 % 2 == 1 && blockSize1 > 1) {
                            bitmapPostThres = AdaptiveThreshToBitmap(imageBitmap,adaptiveMethod1,255.toDouble(),blockSize1,constant1.toDouble())
                            ocr.changeBitmap(bitmapPostThres)
                            ocr.run()
                            ocrResult = ocr.result
                            ocrCounter++
                            Log.d(TAG, ocrCounter.toString())
                            currentPhoneDetectionAccuracy = getPhoneDetectionAccuracy(ocrResult)
                            if (phoneNumberDetected(ocr.result)) {
                                Log.d(TAG,"A full phone number is detected!")
                                phoneNumberOccurrences++
                                result = mutableListOf(adaptiveMethod1,255.0,blockSize1,constant1,phoneNumberOccurrences)
                            }
                            if (currentPhoneDetectionAccuracy > highestPhoneDectectionAccuracy) {
                                highestPhoneDectectionAccuracy = currentPhoneDetectionAccuracy
                            }
                        }
                    }
                }
            }
            return result
        }
        fun getOptimalResizing(imageBitmap: Bitmap, context: Context): MutableList<Double> {
            var result: MutableList<Double> = mutableListOf(0.0,0.0)
            var highestPhoneDectectionAccuracy = 0
            var currentPhoneDetectionAccuracy = 0
            var phoneNumberOccurrences = 0

            val ocr = OcrRunnable(context,imageBitmap)
            var ocrCounter = 0
            var ocrResult: String
            var bitmapPostResize: Bitmap

            for (fx in 1..20) {
                for (fy in 1..20) {
                    bitmapPostResize = dynamicResizeToBitmap(imageBitmap, fx/10.0, fy/10.0)
                    ocr.changeBitmap(bitmapPostResize)
                    ocr.run()
                    ocrResult = ocr.result
                    ocrCounter++
                    Log.d(TAG, "$ocrCounter params are fx: $fx, fy: $fy")
                    currentPhoneDetectionAccuracy = getPhoneDetectionAccuracy(ocrResult)
                    if (phoneNumberDetected(ocr.result)) {
                        Log.d(TAG,"A full phone number is detected!") //291 15 11 -> 1.5 1.1
                        phoneNumberOccurrences++
                        result = mutableListOf(fx/10.0, fy/10.0)
                    }
                    if (currentPhoneDetectionAccuracy > highestPhoneDectectionAccuracy) {
                        highestPhoneDectectionAccuracy = currentPhoneDetectionAccuracy
                        result = mutableListOf(fx/10.0, fy/10.0)
                    }
                }
            }

            return result
        }

    }
}