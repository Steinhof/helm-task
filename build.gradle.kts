import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jooq.meta.jaxb.ForcedType

plugins {
    kotlin("jvm") version "1.5.31"
    kotlin("plugin.spring") version "1.5.31"
    id("org.springframework.boot") version "2.5.5"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("nu.studer.jooq") version "6.0.1"
    id("com.google.cloud.tools.jib") version "3.1.2"
}

group = "com.otus"
version = "0.0.1"
java.sourceCompatibility = JavaVersion.VERSION_16
java.targetCompatibility = JavaVersion.VERSION_16

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-jooq")
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    runtimeOnly("io.r2dbc:r2dbc-postgresql")
    runtimeOnly("org.postgresql:postgresql")
    jooqGenerator("org.postgresql:postgresql")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    implementation("io.micrometer:micrometer-registry-prometheus")
}

jooq {
    configurations {
        create("main") {
            jooqConfiguration.apply {
                logging = org.jooq.meta.jaxb.Logging.WARN
                jdbc.apply {
                    driver = "org.postgresql.Driver"
                    url = "jdbc:postgresql://192.168.49.2:32766/otus"
                    user = "postgres"
                    password = "root"
                }
                generator.apply {
                    name = "org.jooq.codegen.DefaultGenerator"
                    database.apply {
                        name = "org.jooq.meta.postgres.PostgresDatabase"
                        inputSchema = "public"
                        forcedTypes.addAll(
                            arrayOf(
                                ForcedType()
                                    .withName("varchar")
                                    .withIncludeExpression(".*")
                                    .withIncludeTypes("JSONB?"),
                                ForcedType()
                                    .withName("varchar")
                                    .withIncludeExpression(".*")
                                    .withIncludeTypes("INET")
                            ).toList()
                        )
                    }
                    target.apply {
                        packageName = "com.otus.helm"
                        directory = "src/main/db"
                    }
                    generate.apply {
                        isRelations = true
                        isDeprecated = false
                        isRecords = true
                        isPojos = true
                        isFluentSetters = true
                        isJavaTimeTypes = true
                        isSequences = true
                    }
                    strategy.name = "org.jooq.codegen.DefaultGeneratorStrategy"
                }
            }
        }
    }
}



tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "16"
    }
    dependsOn("generateJooq")
}

