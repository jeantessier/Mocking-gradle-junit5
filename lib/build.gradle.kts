plugins {
    id("java")
    id("jvm-test-suite")
    id("jacoco")
}

repositories {
    mavenCentral()
}

dependencies {
    // Use JUnit Jupiter test framework.
    testImplementation(libs.junit.jupiter)

    testImplementation(libs.hamcrest)
    testImplementation(libs.bundles.jmock)
    testImplementation(libs.easymock)
    testImplementation(libs.mockito.core)
}

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

testing {
    suites {
        val test by getting(JvmTestSuite::class) {
            useJUnitJupiter()
        }
    }
}
