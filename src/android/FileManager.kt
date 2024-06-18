package com.kepat.cordova.file.manager

import android.content.Context
import android.content.Intent
import android.net.Uri

import org.apache.cordova.CordovaPlugin
import org.apache.cordova.CallbackContext
import org.json.JSONArray

/**
 * File Manager - Contains the functionality to handle files
 */
class FileManager : CordovaPlugin() {

	/**
	 * Entry point for cordova plugin
	 */
	override fun execute(
		action: String,
		args: JSONArray,
		callbackContext: CallbackContext
	): Boolean {

		// Opening a file
		if (action == "open") {
			// Retrieve the requires parameters
			val filePath: String = args.getString(0)
			// Call the open function
			open(filePath, callbackContext)
			return true
		}

		return false
	}

	/**
	 * To open the file passed with any external installed application
	 */
	private fun open(
		filePath: String,
		callbackContext: CallbackContext
	) {
		// Validate the filepath
		if (filePath.isBlank()) {
			callbackContext.error("Expecting a value for file path (filePath).");
			return;
		}

		// Get the context
		val context: Context = cordova.activity.applicationContext as Context

		// Retrieve the uri
		val helper = Helper(context);
		var fileUri: Uri? = helper.getUri(filePath)

		// Check if uri is valid
		if (fileUri == null) {
			callbackContext.error("Expecting a valid file path (filePath).");
			return;
		}

		// Prepare the required information
		val intent = Intent(Intent.ACTION_VIEW)
		// Pass the data
		intent.setDataAndType(fileUri, context.contentResolver.getType(fileUri))
		// Start activity in a new task
		intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

		// Check if an app can handle the intent
		if (intent.resolveActivity(context.packageManager) != null) {
			// Start the activity
			context.startActivity(intent)
			// Found an app to open a the file
			callbackContext.success("The file can be opened by another app.");
		} else {
			// No app found to open the file
			callbackContext.error("Expecting a valid app to open this file type.");
		}
	}
}