package io.github.ahmedmadhoun.glint

import platform.Foundation.NSLog

/**
 * An iOS-specific [LogWriter] that uses `NSLog`.
 */
class IosLogWriter : LogWriter {
    override fun log(severity: Severity, tag: String, message: String, throwable: Throwable?) {
        NSLog("%@", LogFormatter.format(severity, tag, message, throwable))
    }
}

actual fun platformLogWriter(): LogWriter = IosLogWriter()
