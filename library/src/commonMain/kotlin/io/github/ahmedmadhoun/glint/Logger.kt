package io.github.ahmedmadhoun.glint

/**
 * The core singleton logger.
 */
object Logger {
    private val writers = mutableListOf<LogWriter>(platformLogWriter())

    /**
     * The minimum severity level to log. Messages with a lower severity will be ignored.
     */
    var minSeverity: Severity = Severity.Verbose

    /**
     * Registers an additional [LogWriter].
     */
    fun addWriter(writer: LogWriter) {
        writers.add(writer)
    }

    /**
     * Removes all registered [LogWriter]s.
     */
    fun clearWriters() {
        writers.clear()
    }

    fun v(tag: String, message: String) = log(Severity.Verbose, tag, message, null)
    fun d(tag: String, message: String) = log(Severity.Debug, tag, message, null)
    fun i(tag: String, message: String) = log(Severity.Info, tag, message, null)
    fun s(tag: String, message: String) = log(Severity.Success, tag, message, null)
    fun w(tag: String, message: String) = log(Severity.Warning, tag, message, null)
    fun e(tag: String, message: String, throwable: Throwable? = null) = log(Severity.Error, tag, message, throwable)

    private fun log(severity: Severity, tag: String, message: String, throwable: Throwable?) {
        if (severity.ordinal >= minSeverity.ordinal) {
            writers.forEach { it.log(severity, tag, message, throwable) }
        }
    }

    /**
     * Returns a lightweight wrapper exposing the logging functions without requiring the tag to be passed each time.
     */
    fun withTag(tag: String): TaggedLogger {
        return TaggedLogger(tag)
    }
}
