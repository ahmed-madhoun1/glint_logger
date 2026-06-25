# Glint Logger 🪵✨

[![Maven Central](https://img.shields.io/maven-central/v/io.github.ahmed-madhoun1/glint)](https://central.sonatype.com/artifact/io.github.ahmed-madhoun1/glint)
[![Kotlin](https://img.shields.io/badge/kotlin-multiplatform-blue.svg?logo=kotlin)](https://kotlinlang.org/docs/multiplatform.html)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
![Platform](https://img.shields.io/badge/platform-Android%20%7C%20iOS%20%7C%20JVM%20%7C%20Linux-lightgrey)
[![GitHub stars](https://img.shields.io/github/stars/ahmed-madhoun1/glint_logger?style=social)](https://github.com/ahmed-madhoun1/glint_logger/stargazers)

A lightweight, production-ready logging library for **Kotlin Multiplatform**. 

Glint Logger provides a distinct, highly readable visual output style by wrapping your logs in beautifully formatted text blocks. It seamlessly routes logs to the native logging systems of each platform while maintaining a consistent and clean aesthetic.

---

## 🌟 Features

- 🎨 **Beautiful Formatted Output**: Logs are automatically wrapped in clean ASCII blocks, making them stand out in busy consoles.
- ✅ **Dedicated Success Level**: Includes a unique `Success` level (in addition to standard V, D, I, W, E) to clearly highlight successful operations.
- 🌍 **Multiplatform Support**:
  - **Android**: Routes to `android.util.Log` (Logcat).
  - **iOS**: Routes to native `NSLog`.
  - **Desktop / JVM**: Outputs to terminal with vibrant **ANSI colors**.
  - **Linux**: Supports Linux (x64) targets.
- 🔌 **Extensible**: Easily add custom `LogWriter` implementations to send logs to external services (like Crashlytics, Sentry, or a file).
- 🚀 **Zero Dependencies**: Lightweight and fast, with no external third-party logging requirements.
- 🏷️ **Tagged Loggers**: Create specialized loggers for specific components to avoid repeating tags.

---

## 💻 Supported Platforms

| Platform | Support |
| :--- | :---: |
| **Android** | ✅ |
| **iOS** | ✅ |
| **JVM / Desktop** | ✅ |
| **Linux (x64)** | ✅ |

---

## 📦 Installation

Add the dependency to your `commonMain` source set in `build.gradle.kts`:

```kotlin
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation("io.github.ahmed-madhoun1:glint:1.0.1")
        }
    }
}
```

> **Note:** Ensure you have `mavenCentral()` in your repositories block.

---

## 🚀 Quick Start

### Basic Logging
Import the Logger and start using it anywhere in your common Kotlin code!

```kotlin
import io.github.ahmedmadhoun.glint.Logger
import io.github.ahmedmadhoun.glint.Severity

// Optional: Set minimum severity level (Default is Verbose)
Logger.minSeverity = Severity.Verbose

// Simple logging
Logger.v("Auth", "App is starting up")
Logger.d("Auth", "Initializing system")
Logger.i("Auth", "Loading user data")
Logger.w("Auth", "Network connection is slow")
Logger.e("Auth", "Failed to load profile", Exception("Timeout"))

// Use the unique 'Success' level!
Logger.s("Auth", "User signed in successfully!")
```

### Tagged Logger
Create a `TaggedLogger` to simplify logging within a class:

```kotlin
val authLogger = Logger.withTag("AuthViewModel")

authLogger.d("Fetching tokens...")
authLogger.s("Tokens refreshed successfully!")
```

### Custom Log Writers
Want to send logs to a server or a file? Implement `LogWriter` and add it to the Logger:

```kotlin
import io.github.ahmedmadhoun.glint.LogWriter
import io.github.ahmedmadhoun.glint.Severity

class MyCustomWriter : LogWriter {
    override fun log(severity: Severity, tag: String, message: String, throwable: Throwable?) {
        // Send to your analytics or file system
        if (severity == Severity.Error) {
            // e.g., Crashlytics.logException(throwable)
        }
    }
}

Logger.addWriter(MyCustomWriter())
```

> **Tip:** By default, Glint Logger includes the platform-specific log writer (e.g., Logcat for Android, NSLog for iOS). If you want to disable the default writer and *only* use your custom writers, you can clear the existing writers first:

```kotlin
// Remove the default platform log writer
Logger.clearWriters()

// Add your custom writers
Logger.addWriter(MyCustomWriter())
```

---

## 📝 Severity Levels

| Level | Method | Label | Use Case |
|-------|--------|-------|----------|
| **Verbose** | `Logger.v()` | `[VERBOSE]` | Fine-grained tracing information. |
| **Debug** | `Logger.d()` | `[DEBUG]` | Useful information for developers. |
| **Info** | `Logger.i()` | `[INFO]` | General application flow events. |
| **Success** | `Logger.s()` | `[SUCCESS]` | **Positive outcomes (API success, DB saved).** |
| **Warning** | `Logger.w()` | `[WARNING]` | Unexpected behavior that isn't an error. |
| **Error** | `Logger.e()` | `[ERROR]` | Critical issues and exceptions. |

---

## 🖥️ Console Preview

Here is how your logs will look in the console.

```text
------------------------------------------------------------
| [DEBUG]  *  Tag: Auth
| Initializing system
------------------------------------------------------------
------------------------------------------------------------
| [SUCCESS]  *  Tag: Auth
| User signed in successfully!
------------------------------------------------------------
------------------------------------------------------------
| [ERROR]  *  Tag: Auth
| Failed to load profile
|
| java.lang.Exception: Timeout exception
| 	at com.example.app.AuthViewModel.loadProfile(AuthViewModel.kt:42)
------------------------------------------------------------
```

---

## 🤝 Contributing
Contributions are welcome! Feel free to open an issue or submit a pull request.

---

## 📄 License
This project is licensed under the MIT License.

---

### Created by **Ahmed Madhoun** 👨‍💻

**If you find this library useful, please give it a ⭐ on GitHub! It helps more developers discover the project.**
