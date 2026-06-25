package io.github.ahmedmadhoun.glint

/**
 * A Linux-specific fallback [LogWriter].
 */
actual fun platformLogWriter(): LogWriter = ConsoleLogWriter()
