plugins {
    kotlin("js") version "1.4.30"
    kotlin("plugin.serialization") version "1.4.10"
}

group = "com.adedom.todolistweb"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
    mavenCentral()
    maven { url = uri("https://dl.bintray.com/kotlin/kotlin-js-wrappers") }
}

dependencies {
    testImplementation(kotlin("test-js"))
    implementation("org.jetbrains:kotlin-react:16.13.1-pre.113-kotlin-1.4.0")
    implementation("org.jetbrains:kotlin-react-dom:16.13.1-pre.113-kotlin-1.4.0")
    implementation("org.jetbrains:kotlin-styled:1.0.0-pre.113-kotlin-1.4.0")
    implementation("org.jetbrains:kotlin-react-router-dom:5.1.2-pre.113-kotlin-1.4.0")
    implementation("org.jetbrains:kotlin-redux:4.0.0-pre.113-kotlin-1.4.0")
    implementation("org.jetbrains:kotlin-react-redux:5.0.7-pre.113-kotlin-1.4.0")

    val ktorVersion = "1.5.1"
    implementation("io.ktor:ktor-client-js:$ktorVersion")
    implementation("io.ktor:ktor-client-logging-js:$ktorVersion")
    implementation("io.ktor:ktor-client-serialization:$ktorVersion")

    val coroutines = "1.4.2"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:$coroutines")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines")

}

kotlin {
    js(LEGACY) {
        browser {
            binaries.executable()
            webpackTask {
                cssSupport.enabled = true
            }
            runTask {
                cssSupport.enabled = true
            }
            testTask {
                useKarma {
                    useChromeHeadless()
                    webpackConfig.cssSupport.enabled = true
                }
            }
        }
    }
}

tasks.register("stage") {
    dependsOn("build")
}
