package io.github.ahmedmadhoun.glint

import android.util.Log

/**
 * An Android-specific [LogWriter] that uses `android.util.Log`.
 */
class AndroidLogWriter : LogWriter {
    override fun log(severity: Severity, tag: String, message: String, throwable: Throwable?) {
        val formattedMessage = LogFormatter.format(severity, tag, message, throwable)
        when (severity) {
            Severity.Verbose -> Log.v(tag, formattedMessage)
            Severity.Debug -> Log.d(tag, formattedMessage)
            Severity.Info -> Log.i(tag, formattedMessage)
            Severity.Success -> Log.i(tag, formattedMessage)
            Severity.Warning -> Log.w(tag, formattedMessage)
            Severity.Error -> Log.e(tag, formattedMessage)
        }
    }
}

actual fun platformLogWriter(): LogWriter = AndroidLogWriter()
