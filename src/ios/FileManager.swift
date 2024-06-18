import UIKit

typealias SwiftFileManager = Foundation.FileManager

@objc(FileManager)
class FileManager : CDVPlugin {
    // Command Callback ID
    private var commandCallbackId: String?

    /*
     * Open the file with any available external mobile app available
     * depending on the extension type
     */
    @objc(open:)
	func open (command: CDVInvokedUrlCommand) {
        // Assign the callback id
        self.commandCallbackId = command.callbackId 

        // Retrieve the file path argument
        let filePath = command.arguments[0] as! String

        // Check if filePath is not empty
        if filePath.isEmpty {
            callback(CDVCommandStatus_ERROR, "Expecting a value for file path (filePath).")
            return
        }

        // Convert the string to an URL
        guard let url = URL(string: filePath) else {
            callback(CDVCommandStatus_ERROR, "Expecting a valid file path (filePath).")
            return 
        }

        // Check if the file exists before attempting to open
        if !SwiftFileManager.default.fileExists(atPath: url.path) {
            callback(CDVCommandStatus_ERROR, "Expecting a valid file path (filePath).")
            return 
        }

        // Open the file with other app
        let activityViewController = UIActivityViewController(activityItems: [url], applicationActivities: nil)
        self.viewController.present(activityViewController, animated: true)
        self.callback(CDVCommandStatus_OK, "The file can be opened by another app.")
    }

    /**
     * Cordova callback result function
     */
    func callback(_ status: CDVCommandStatus, _ message: String) {
        commandDelegate.run(inBackground: {
            // Execute the cordova result
            self.commandDelegate!.send(
                CDVPluginResult(
                    status: status,
                    messageAs: message
                ),
                callbackId: self.commandCallbackId
            )
        })
    }
}