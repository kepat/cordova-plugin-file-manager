package com.kepat.cordova.file.manager

import android.content.Context
import android.net.Uri
import android.os.Build

import androidx.core.content.FileProvider

import java.io.File
import java.net.URISyntaxException

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
        // Try parsing as uri directly first
        try {
            return Uri.parse(filePath)
        } catch (error: URISyntaxException) {
            // Try checking as a file path
            val file = File(filePath)

            // Check if file exists
            if (!file.exists()) {
                return null
            }

            // Retrieve the uri
            // Handle potential issues with external storage (Android 10+)
            val fileUri: Uri
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                fileUri = FileProvider.getUriForFile(context, context.packageName + ".provider", file)
            } else {
                fileUri = Uri.fromFile(file)
            }

            return fileUri
        }

        return null
    }

}
