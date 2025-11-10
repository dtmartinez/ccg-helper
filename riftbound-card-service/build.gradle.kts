import com.google.protobuf.gradle.*

val ioRpcVersion = "1.75.0"
val ioKotlinRpcVersion = "1.5.0"
val googleProtobufCompilerVersion = "4.32.1"
val googleCommonProtosVersion = "2.61.2"
val coroutinesVersion = "1.10.2"
val testcontainersBomVersion = "1.21.3"

plugins {
    kotlin("jvm") version "2.2.10"
    kotlin("plugin.spring") version "2.2.10"
    id("org.springframework.boot") version "4.0.0-M3"
    id("io.spring.dependency-management") version "1.1.7"
    id("com.google.protobuf") version "0.9.5"
}

group = "ccghelper"
version = "0.0.1-SNAPSHOT"
description = "Service that handles the cards"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(24)
    }
}

repositories {
    mavenCentral()
    maven(url = "https://repo.spring.io/milestone")
}

dependencies {
    // Spring dependencies
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.kafka:spring-kafka")
    implementation("org.springframework.boot:spring-boot-starter-data-cassandra")
    implementation("org.springframework.boot:spring-boot-starter-graphql")

    // Protobuf
    implementation("com.google.protobuf:protobuf-kotlin:$googleProtobufCompilerVersion")
    implementation("com.google.api.grpc:proto-google-common-protos:$googleCommonProtosVersion")

    // gRPC
    implementation("io.grpc:grpc-protobuf:$ioRpcVersion")
    implementation("io.grpc:grpc-netty-shaded:$ioRpcVersion")
    implementation("io.grpc:grpc-stub:$ioRpcVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    implementation("io.grpc:grpc-kotlin-stub:$ioKotlinRpcVersion")

    //Kotlin support
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    //Testing
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("io.grpc:grpc-testing:$ioRpcVersion")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // Test containers
    testImplementation(platform("org.testcontainers:testcontainers-bom:$testcontainersBomVersion")) //BOM
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:kafka")
    testImplementation("org.testcontainers:cassandra")
    testImplementation("org.testcontainers:elasticsearch")

}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict", "-Xannotation-default-target=param-property")
    }

    sourceSets {
        val main by getting {
            kotlin.srcDirs(
                "build/generated/source/proto/main/kotlin",
                "build/generated/source/proto/main/grpckt"
            )
        }
    }
}

sourceSets["main"].java.srcDirs(
    "build/generated/source/proto/main/java",
    "build/generated/source/proto/main/grpc"
)

tasks.withType<Test> {
    useJUnitPlatform()
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:$googleProtobufCompilerVersion"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:$ioRpcVersion"
        }

        id("grpckt") {
            artifact = "io.grpc:protoc-gen-grpc-kotlin:$ioKotlinRpcVersion:jdk8@jar"
        }
    }

    generateProtoTasks {
        ofSourceSet("main").forEach{ task ->
            task.plugins{
                id("grpc")
                id("grpckt")
            }

            task.builtins{
                id("kotlin")
            }
        }
    }
}
