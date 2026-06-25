package io.github.ahmedmadhoun.glint

/**
 * A lightweight wrapper around [Logger] that automatically applies a specific tag to all log messages.
 */
class TaggedLogger(private val tag: String) {
    fun v(message: String) = Logger.v(tag, message)
    fun d(message: String) = Logger.d(tag, message)
    fun i(message: String) = Logger.i(tag, message)
    fun s(message: String) = Logger.s(tag, message)
    fun w(message: String) = Logger.w(tag, message)
    fun e(message: String, throwable: Throwable? = null) = Logger.e(tag, message, throwable)
}
