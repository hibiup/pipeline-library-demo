plugins {
    java
    groovy
    id("idea")
    id("com.mkobit.jenkins.pipelines.shared-library") version "0.10.1"
    id("com.github.ben-manes.versions") version "0.21.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

tasks {
    wrapper {
        gradleVersion = "7.4.1"
    }
}

test {
    useJUnitPlatform()
}

buildDir = file("target")

sourceSets {
    named("main") {
        groovy {
            setSrcDirs(listOf("src"))
        }
        resources {
            setSrcDirs(listOf("vars", "resources"))
        }

        output.resourcesDir = file("$buildDir/classes")
    }

    named("test") {
        groovy {
            setSrcDirs(listOf("test/unit"))
        }
        resources {
            setSrcDirs(listOf("test/resources"))
        }
    }

    named("integrationTest") {
        groovy {
            setSrcDirs(listOf("test/integration"))
        }
        resources {
            setSrcDirs(listOf("test/resources"))
        }
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // Groovy version
    implementation("org.codehaus.groovy:groovy:2.4.12")

    // Spock
    val spock = "org.spockframework:spock-core:1.2-groovy-2.4"
    testImplementation(spock)
    integrationTestImplementation(spock)

    // JUnit
    val junitJupiter = "org.junit.jupiter:junit-jupiter-api:5.8.1"
    val junitJupiterEngine = "org.junit.jupiter:junit-jupiter-engine:5.8.1"
    testImplementation(junitJupiter)
    testRuntimeOnly(junitJupiterEngine)

    // Jenkins
    val jenkinsSpock= "com.homeaway.devtools.jenkins:jenkins-spock:2.1.5"
    val jenkinsCore = "org.jenkins-ci.main:jenkins-core:2.102"
    val jenkinsJunit = "org.jenkins-ci.plugins:junit:1.24"
    testImplementation(jenkinsSpock)
    testImplementation(jenkinsCore)
    testImplementation(jenkinsJunit)
    integrationTestImplementation(jenkinsSpock)
    integrationTestImplementation(jenkinsCore)
    integrationTestImplementation(jenkinsJunit)

    // provides sh() step mock
    val jenkinsTaskStep  = "org.jenkins-ci.plugins.workflow:workflow-durable-task-step:2.17"
    testImplementation(jenkinsTaskStep)
    integrationTestImplementation(jenkinsTaskStep)

    // provide echo() mock
    val  jenkinsBasicStep = "org.jenkins-ci.plugins.workflow:workflow-basic-steps:2.6"
    testImplementation(jenkinsBasicStep)
    integrationTestImplementation(jenkinsBasicStep)

    // Fix a icu bug
    val icu4j = "com.ibm.icu:icu4j:3.4.4"
    testImplementation(icu4j)
}
