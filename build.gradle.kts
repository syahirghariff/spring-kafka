plugins {
	java
	id("org.springframework.boot") version "3.4.1"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.dev"
version = "0.0.1-SNAPSHOT"

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
	implementation("org.springframework.kafka:spring-kafka:3.3.1")
	implementation("org.springframework.boot:spring-boot-starter-validation:3.4.1")
	implementation("org.springframework.boot:spring-boot-starter-web:3.4.1")
	implementation ("org.apache.commons:commons-lang3:3.17.0")
	implementation("com.fasterxml.jackson.core:jackson-databind:2.18.3")
	implementation("org.springframework.boot:spring-boot-starter-actuator")

	// Lombok
	compileOnly("org.projectlombok:lombok:1.18.36")
	annotationProcessor("org.projectlombok:lombok:1.18.36")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
