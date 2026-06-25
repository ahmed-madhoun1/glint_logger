package io.github.ahmedmadhoun.glint

/**
 * The contract for any log destination (e.g. console, Android Logcat, iOS NSLog).
 */
interface LogWriter {
    /**
     * Writes a log message to the destination.
     *
     * @param severity The severity level of the log message.
     * @param tag A string identifying the source of the log message.
     * @param message The actual log message.
     * @param throwable An optional exception associated with the log message.
     */
    fun log(severity: Severity, tag: String, message: String, throwable: Throwable?)
}

/**
 * Returns the default [LogWriter] for the current platform.
 */
expect fun platformLogWriter(): LogWriter
