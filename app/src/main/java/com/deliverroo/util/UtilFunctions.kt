package com.deliverroo.util

import android.content.Context
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

class UtilFunctions {


    companion object {
        private const val TAG = "util"
         fun getDirContents(dir: File) {
            val files: Array<File> = dir.listFiles()
            Log.d(TAG, "Size: " + files.size)
            for (i in files.indices) {
                Log.d(TAG, "FileName:" + files[i].name)
            }
        }
        fun createTessdataFolder(context: Context) {
            val tessdataFolder = File(context.filesDir,"tessdata")
            tessdataFolder.mkdir()
        }

         fun checkFileExists(file: File) {
            if (file.exists()) {
                Log.d(TAG, "$file does exist")
            } else {
                Log.d(TAG, "$file does NOT exist")
            }
        }

         fun copyTessAssetToInternalFolder(context: Context) {
             val path = context.filesDir.absolutePath + "/tessdata/"
            val run = Runnable {
                val assetManager = context.assets
                var out: OutputStream? = null
                try {
                    val inputStream = assetManager.open("heb.traineddata")
                    Log.d(TAG, "$inputStream")
                    val tesspath = path
                    val tessFolder = File(tesspath)
                    if (!tessFolder.exists()) tessFolder.mkdir()
                    val tessData = "$tesspath/heb.traineddata"
                    val tessFile = File(tessData)
                    Log.d(TAG, "$tessFile")
                    if (!tessFile.exists()) {
                        out = FileOutputStream(tessData)
                        val buffer = ByteArray(1024)
                        var read = inputStream.read(buffer)
                        while (read != -1) {
                            out.write(buffer, 0, read)
                            read = inputStream.read(buffer)
                        }
                        Log.d(TAG, " Did finish copy tess file  ")
                    } else Log.d(TAG, " tess file exist  ")
                } catch (e: Exception) {
                    Log.d(
                        "test",
                        "couldn't copy with the following error : $e"
                    )
                } finally {
                    try {
                        out?.close()
                    } catch (exx: Exception) {
                    }
                }
            }
            Thread(run).start()
        }
    }
}
