package com.deliverroo.util

import android.graphics.Bitmap
import org.opencv.android.Utils
import org.opencv.core.Mat
import org.opencv.core.Size
import org.opencv.imgcodecs.Imgcodecs
import org.opencv.imgproc.Imgproc


class OpenCV {
    companion object {
        fun makeGray(bitmap: Bitmap) : Bitmap {

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
        @param method which threshold to perform, 1 for mean, 2 for gaussian
         */
        fun AdaptiveThresh(filePath: String, methodParam: Int) : Unit{
            var method = 0
            if (methodParam == 1) {
                method = Imgproc.ADAPTIVE_THRESH_MEAN_C
            } else {
                method = Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C
            }
            // Reading the image
            val src = Imgcodecs.imread(filePath, 0)

            // Creating an empty matrix to store the result
            val dst = Mat()

            Imgproc.adaptiveThreshold(
                src, dst, 125.0, method,
                Imgproc.THRESH_BINARY, 11, 12.0
            )
            // Writing the image
            Imgcodecs.imwrite("sdcard/Download/ocrimages/atb2.jpg", dst)
            println("Image Processed")
        }
        fun erode(filePath: String): Unit {
            val src = Imgcodecs.imread(filePath, 0)
            //Creating destination matrix
            val dst = Mat(src.rows(), src.cols(), src.type())
            //Preparing the kernel matrix object
            //Preparing the kernel matrix object
            val kernel =
                Imgproc.getStructuringElement(Imgproc.MORPH_RECT, Size(2 * 2 + 1.0, 2 * 2 + 1.0))
            Imgproc.erode(src, dst, kernel)
            Imgcodecs.imwrite("sdcard/Download/ocrimages/atb6.jpg", dst)
        }
        fun dilate(filePath: String): Unit {
            val src = Imgcodecs.imread(filePath, 0)
            //Creating destination matrix
            val dst = Mat(src.rows(), src.cols(), src.type())
            //Preparing the kernel matrix object
            //Preparing the kernel matrix object
            val kernel =
                Imgproc.getStructuringElement(Imgproc.MORPH_RECT, Size(2 * 2 + 1.0, 2 * 2 + 1.0))
            Imgproc.dilate(src, dst, kernel)
            Imgcodecs.imwrite("sdcard/Download/ocrimages/atb5.jpg", dst)
        }
    }
}