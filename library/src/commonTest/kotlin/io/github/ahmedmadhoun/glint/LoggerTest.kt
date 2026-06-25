package io.github.ahmedmadhoun.glint

import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.test.assertFalse
import kotlin.test.BeforeTest

class LoggerTest {

    @BeforeTest
    fun setup() {
        // Ensure a clean state for every test
        Logger.clearWriters()
        Logger.minSeverity = Severity.Verbose
    }

    class FakeLogWriter : LogWriter {
        val logs = mutableListOf<String>()
        override fun log(severity: Severity, tag: String, message: String, throwable: Throwable?) {
            logs.add("${severity.label}|$tag|$message")
        }
    }

    @Test
    fun testMinSeverityFiltering() {
        val fakeWriter = FakeLogWriter()
        Logger.addWriter(fakeWriter)
        
        Logger.minSeverity = Severity.Warning
        
        val uniqueMsg = "UniqueInfoMsg"
        Logger.i("TestTag", uniqueMsg)
        assertFalse(fakeWriter.logs.any { it.contains(uniqueMsg) }, "Message below minSeverity should be filtered")
        
        val uniqueWarn = "UniqueWarnMsg"
        Logger.w("TestTag", uniqueWarn)
        assertTrue(fakeWriter.logs.any { it.contains(uniqueWarn) }, "Message at minSeverity should not be filtered")
    }

    @Test
    fun testMessageReachesEveryWriter() {
        val fake1 = FakeLogWriter()
        val fake2 = FakeLogWriter()
        
        Logger.addWriter(fake1)
        Logger.addWriter(fake2)
        
        val uniqueErr = "UniqueErrorMsg"
        Logger.e("TestError", uniqueErr)
        
        assertTrue(fake1.logs.any { it.contains(uniqueErr) }, "First writer should receive the message")
        assertTrue(fake2.logs.any { it.contains(uniqueErr) }, "Second writer should receive the message")
    }

    @Test
    fun testWithTag() {
        val fakeWriter = FakeLogWriter()
        Logger.addWriter(fakeWriter)
        
        val taggedLogger = Logger.withTag("MyCustomTag")
        taggedLogger.s("Success message")
        
        assertTrue(fakeWriter.logs.any { it == "SUCCESS|MyCustomTag|Success message" }, "Tagged logger should use the provided tag")
    }
}
