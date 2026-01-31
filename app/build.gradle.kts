import org.springframework.boot.gradle.tasks.bundling.BootJar
import org.gradle.jvm.tasks.Jar

plugins {
    // Re-apply spring-boot plugin to enable boot features
    // Actually already applied in root subprojects block, but we need to configure tasks
}

dependencies {
    implementation(project(":modules:catalog"))
    implementation(project(":modules:order"))
    implementation(libs.spring.boot.starter.web)
    testImplementation(libs.spring.boot.starter.test)
}

tasks.named<BootJar>("bootJar") {
    enabled = true
}

tasks.named<Jar>("jar") {
    enabled = false
}
