import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.3.0.RC1"
	id("io.spring.dependency-management") version "1.0.9.RELEASE"
	id("com.google.cloud.tools.jib") version "1.3.0"
	kotlin("jvm") version "1.3.72"
	kotlin("plugin.spring") version "1.3.72"
}

val group = "io.jrb.msvc"
val version = "0.0.1-SNAPSHOT"
val buildNumber by extra("0")

java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/milestone") }
}

dependencies {
	implementation("com.google.guava:guava:28.2-jre")
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("com.github.java-json-tools:json-patch:1.12")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	implementation("io.github.microutils:kotlin-logging:1.7.9")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
	testImplementation("io.projectreactor:reactor-test")
	testImplementation("io.mockk:mockk:1.10.0")
	testImplementation("org.apache.commons:commons-lang3")
}

jib {
	to {
		image = "brulejr/songs-microservice"
		tags = setOf("$version", "$version.${extra["buildNumber"]}")
		auth {
			username = System.getenv("DOCKERHUB_CREDENTIALS_USR") ?: "username"
			password = System.getenv("DOCKERHUB_CREDENTIALS_PSW") ?: "password"
		}
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}
