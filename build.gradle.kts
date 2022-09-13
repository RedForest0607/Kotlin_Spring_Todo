import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("plugin.jpa") version "1.6.21"
	id("org.springframework.boot") version "2.7.3"
	id("io.spring.dependency-management") version "1.0.13.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
	id("org.asciidoctor.jvm.convert") version "3.3.2"
}

group = "com.example.ToyProject"
version = "5.4.2"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-mustache")
	implementation("org.springframework.boot:spring-boot-starter-web")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("io.springfox:springfox-boot-starter:3.0.0")
	testImplementation("io.kotest:kotest-runner-junit5:5.4.2")
	testImplementation("io.kotest:kotest-assertions-core:5.4.2")
	testImplementation ("io.kotest:kotest-property:$version")
	testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.2")
	implementation("org.junit.jupiter:junit-jupiter:5.9.0")
	runtimeOnly("com.h2database:h2")
	runtimeOnly("org.springframework.boot:spring-boot-devtools")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict"
		)
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
