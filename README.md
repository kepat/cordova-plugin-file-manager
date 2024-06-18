---
title: File Manager
description: File manager that contains functionlity in handling files.
---

# cordova-plugin-file-manager

This is a cordova plugin that manages and create helpers for file.

## Installation

To install via repo url directly.

    cordova plugin add https://github.com/kepat/cordova-plugin-file-manager

### Quirks

This requires cordova 10.0+.

---

# API Reference

* [fileManager](#module_fileManager)
    * [.open(filePath, successCallback, errorCallback)](#module_fileManager.open)

---

<a name="module_fileManager"></a>

## fileDetails

<a name="module_fileManager.open"></a>

### fileManager.open(filepath, successCallback, errorCallback)
Open the file using any application available in the mobile app.

__Supported Platforms__

- Android
- iOS

__Inputs__

| Param | Type | Description |
| --- | --- | --- |
| filePath | <code>string</code> | The location of the file, accepts both local path or uri. |
| successCallback | <code>function</code> | The callback for success. |
| errorCallback | <code>function</code> | The callback for error. |

**Example**  
```js
let filePath = "/var/../..";

emobiq.fileManager.open(
    filePath,
    function(data) {
        console.log(data);
    }, 
    function(error) {
        console.log(error);
    }
);
```