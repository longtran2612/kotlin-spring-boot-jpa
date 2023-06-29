import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.1.1"
	id("io.spring.dependency-management") version "1.1.0"
	kotlin("jvm") version "1.8.22"
	kotlin("plugin.spring") version "1.8.22"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")

	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
//	implementation("jakarta.persistence:jakarta.persistence-api")
	//ORACLE DRIVER
	implementation("com.oracle.database.jdbc:ojdbc8")
	// high performance connection pool
//	implementation("com.zaxxer:HikariCP:4.0.3")
//	// hibernate
//	implementation("org.hibernate:hibernate-core:5.6.3.Final")
//	// hibernate entity manager
//	implementation("org.hibernate:hibernate-entitymanager:5.6.3.Final")

	// model mapper
	implementation("org.modelmapper:modelmapper:2.4.4")

	// commons lang3
	implementation("org.apache.commons:commons-lang3:3.12.0")
	// validator
	implementation("org.springframework.boot:spring-boot-starter-validation")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
