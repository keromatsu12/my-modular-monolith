dependencies {
    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.modulith.starter.core)
    implementation(project(":modules:order"))
    testImplementation(libs.spring.modulith.starter.test)
}
