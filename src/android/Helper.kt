package com.kepat.cordova.file.manager

import android.content.Context
import android.net.Uri
import android.os.Build

import androidx.core.content.FileProvider

import java.io.File
import java.net.MalformedURLException
import java.net.URL

/**
 * Helper - Contains the helper to manage the uri 
 */
class Helper(val context: Context) {
    
    /**
	 * To retrieve the uri from a string
     *
     * @param filepath - translate the filePath to a valid uri
     *
     * @return  Uri
	 */
	public fun getUri(
		filePath: String
	): Uri? {
        try {
            // Try parsing the uri to file first
            val file = File(URL(filePath).toURI())

            // Check if file exists
            if (!file.exists()) {
                return null
            }

            // Retrieve the accessible content
            val fileUri: Uri
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                fileUri = FileProvider.getUriForFile(context, context.packageName + ".provider", file)
            } else {
                fileUri = Uri.fromFile(file)
            }
            return fileUri
        } catch (error: MalformedURLException) {
            // Try parsing to a Uri
            return Uri.parse(filePath)
        }

        return null
    }

}