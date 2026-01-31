dependencies {
    implementation(project(":modules:common"))
    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.modulith.starter.core)
    testImplementation(libs.spring.modulith.starter.test)
}
