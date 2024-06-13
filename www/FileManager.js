// Simplify some cordova functions to be used
var exec = require('cordova/exec');
var argscheck = require('cordova/argscheck');
var getValue = argscheck.getValue;

/**
 * @exports fetch
 */
var fileManagerExport = {};

/**
 * Open File Function
 * @param {string} filePath - file path of the file to be opened
 * @param {function} successCallback - function triggered if successful
 * @param {function} errorCallback - function triggered if failed
 */
fileManagerExport.open = function (filePath, successCallback, errorCallback) {
    // Prepare the arguments to be passed to the native function
    var args = [];
    args.push(getValue(filePath, false)); // File Path

    exec(successCallback, errorCallback, 'FileManager', 'open', args);
};

// Export the functions
module.exports = fileManagerExport;