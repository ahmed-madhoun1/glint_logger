package io.github.ahmedmadhoun.glint

internal object LogFormatter {
    private const val LINE_LENGTH = 60
    private val BORDER_LINE = "-".repeat(LINE_LENGTH)

    fun format(
        severity: Severity,
        tag: String,
        message: String,
        throwable: Throwable? = null,
        includeColor: Boolean = false,
        colorCode: String = "",
        resetCode: String = ""
    ): String {
        val builder = StringBuilder()
        
        if (includeColor) builder.append(colorCode)
        builder.append(BORDER_LINE).append("\n")
        builder.append("| [${severity.label}]  *  Tag: $tag\n")
        
        // Handle multi-line messages
        message.split("\n").forEach { line ->
            builder.append("| $line\n")
        }
        
        if (throwable != null) {
            builder.append("|\n")
            val stackTrace = throwable.stackTraceToString()
            stackTrace.split("\n").forEach { line ->
                builder.append("| $line\n")
            }
        }
        
        builder.append(BORDER_LINE)
        if (includeColor) builder.append(resetCode)
        
        return builder.toString()
    }
}
