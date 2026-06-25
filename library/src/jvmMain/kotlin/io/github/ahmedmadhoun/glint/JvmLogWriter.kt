package io.github.ahmedmadhoun.glint

/**
 * A JVM-specific [LogWriter] that uses ANSI escape codes for terminal color output.
 */
class JvmLogWriter : LogWriter {

    private val ANSI_RESET = "\u001B[0m"
    private val ANSI_GREEN = "\u001B[32m"
    private val ANSI_YELLOW = "\u001B[33m"
    private val ANSI_RED = "\u001B[31m"

    override fun log(severity: Severity, tag: String, message: String, throwable: Throwable?) {
        val colorCode = when (severity) {
            Severity.Success -> ANSI_GREEN
            Severity.Warning -> ANSI_YELLOW
            Severity.Error -> ANSI_RED
            else -> ""
        }

        println(LogFormatter.format(severity, tag, message, throwable, includeColor = colorCode.isNotEmpty(), colorCode = colorCode, resetCode = ANSI_RESET))
    }
}

actual fun platformLogWriter(): LogWriter = JvmLogWriter()
