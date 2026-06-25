package io.github.ahmedmadhoun.glint

/**
 * A default fallback [LogWriter] that prints to the standard console.
 */
class ConsoleLogWriter : LogWriter {
    override fun log(severity: Severity, tag: String, message: String, throwable: Throwable?) {
        println(LogFormatter.format(severity, tag, message, throwable))
    }
}
