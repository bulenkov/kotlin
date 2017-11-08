import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

apply { plugin("kotlin") }

dependencies {
    compileOnly(project(":core:util.runtime"))
    compileOnly(project(":core:descriptors"))
    compileOnly(project(":core:descriptors.jvm"))

    testCompile(projectTests(":compiler:tests-common"))
    testCompile(projectTests(":generators:test-generator"))
}

sourceSets {
    "main" { projectDefault() }
    "test" { projectDefault() }
}

tasks.withType<JavaCompile> {
    if (name == "compileJava") {
        sourceCompatibility = "1.6"
        targetCompatibility = "1.6"
    }
}

tasks.withType<KotlinCompile> {
    if (name == "compileKotlin") {
        kotlinOptions.jvmTarget = "1.6"
        kotlinOptions.jdkHome = rootProject.extra["JDK_16"] as String
    }
}

val generateTests by generator("org.jetbrains.kotlin.generators.tests.GenerateRuntimeDescriptorTestsKt")

projectTest {
    workingDir = rootDir
}
