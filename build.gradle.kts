import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar
import org.gradle.jvm.tasks.Jar
import org.gradle.api.tasks.testing.Test

plugins {
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.spring) apply false
    alias(libs.plugins.spring.boot) apply false
    alias(libs.plugins.spring.dependency.management) apply false
}

allprojects {
    group = "com.example.modularmonolith"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}

val rootLibs = libs

subprojects {
    if (name == "modules") {
        return@subprojects
    }

    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "io.spring.dependency-management")

    if (name == "app") {
        apply(plugin = "org.springframework.boot")
    } else {
        configure<io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension> {
            imports {
                mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
            }
        }
    }

    configure<JavaPluginExtension> {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(21))
        }
    }

    dependencies {
        add("implementation", rootLibs.kotlin.reflect)
        add("implementation", rootLibs.jackson.module.kotlin)
        add("testImplementation", rootLibs.spring.boot.starter.test)
    }

    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            freeCompilerArgs += "-Xjsr305=strict"
            jvmTarget = "21"
        }
    }

    tasks.withType<Test>().configureEach {
        useJUnitPlatform()
    }

    // Disable bootJar by default (enable only in app via its build.gradle.kts)
    tasks.withType<BootJar>().configureEach {
        enabled = false
    }

    tasks.withType<Jar>().configureEach {
        enabled = true
    }
}
