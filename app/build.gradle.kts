plugins {
    application
    checkstyle
    id("org.springframework.boot") version "3.5.7"
    id("io.spring.dependency-management") version "1.1.7"
    id("io.freefair.lombok") version "8.6"
    id("org.sonarqube") version "7.0.1.6134"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("jacoco")
}

group = "hexlet.code"
version = "0.0.1-SNAPSHOT"
description = "app"

application {
    mainClass.set("hexlet.code.AppApplication")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")


    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

}

sonar {
    properties {
        property("sonar.projectKey", "vvvilkha_java-project-99")
        property("sonar.organization", "vvvilkha")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.jacocoTestReport {
    reports {
        xml.required = true
        csv.required = false
        html.outputLocation = layout.buildDirectory.dir("jacocoHtml")
    }
}
