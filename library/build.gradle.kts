import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.vanniktech.mavenPublish)
}

group = "io.github.ahmed-madhoun1"

version = "1.0.1"

kotlin {
    jvm()
    androidLibrary {
        namespace = "io.github.ahmedmadhoun.glint"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()

        withJava() // enable java compilation support
        withHostTestBuilder {}.configure {}
        withDeviceTestBuilder { sourceSetTreeName = "test" }

        compilerOptions { jvmTarget = JvmTarget.JVM_11 }
    }
    iosArm64()
    iosSimulatorArm64()
    linuxX64()

    sourceSets {
        commonMain.dependencies {
            // put your multiplatform dependencies here
        }

        commonTest.dependencies { implementation(libs.kotlin.test) }
    }
}

mavenPublishing {
    publishToMavenCentral()

    signAllPublications()

    coordinates(group.toString(), "glint", version.toString())

    pom {
        name = "Glint Logger"
        description = "A lightweight, production-ready Kotlin Multiplatform logging library with beautiful ASCII boxed console output and a unique Success severity level."
        inceptionYear = "2026"
        url = "https://github.com/ahmed-madhoun1/glint_logger"
        licenses {
            license {
                name = "MIT License"
                url = "https://opensource.org/licenses/MIT"
                distribution = "repo"
            }
        }
        developers {
            developer {
                id = "ahmed-madhoun1"
                name = "Ahmed Madhoun"
                url = "https://github.com/ahmed-madhoun1"
            }
        }
        scm {
            url = "https://github.com/ahmed-madhoun1/glint_logger"
            connection = "scm:git:git://github.com/ahmed-madhoun1/glint_logger.git"
            developerConnection = "scm:git:ssh://github.com/ahmed-madhoun1/glint_logger.git"
        }
    }
}
