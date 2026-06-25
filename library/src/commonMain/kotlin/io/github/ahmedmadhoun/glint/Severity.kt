package io.github.ahmedmadhoun.glint

/**
 * Represents the severity level of a log message.
 *
 * @property label A short string for display (e.g. "VERBOSE", "DEBUG").
 * @property icon A visual marker/icon string for console-friendly platforms.
 */
enum class Severity(val label: String, val icon: String) {
    Verbose("VERBOSE", "V"),
    Debug("DEBUG", "D"),
    Info("INFO", "I"),
    Success("SUCCESS", "S"),
    Warning("WARNING", "W"),
    Error("ERROR", "E")
}
