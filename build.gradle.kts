plugins {
    val kotlinVersion = "1.8.10"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion
    id("net.mamoe.mirai-console") version "2.16.0"
//    id("java")
}

group = "io.github.baka4n"
version = "1.0-SNAPSHOT"

repositories {
    maven("https://maven.aliyun.com/repository/public") // 阿里云国内代理仓库
    mavenCentral()

}

mirai {
    jvmTarget = JavaVersion.VERSION_17
}

tasks.withType<JavaExec> {
    jvmArgs = listOf("-Dmirai.message.allow.sending.file.message=true", "-Dfile.encoding=UTF-8")
}

dependencies {
    compileOnly("com.google.auto.service:auto-service:1.1.1")
    annotationProcessor("com.google.auto.service:auto-service:1.1.1")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}