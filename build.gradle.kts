
plugins {
    `java-library`
    `maven-publish`
}

group = "com.nexalog"
version = "0.1.0"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.3")
}


tasks.test {
    useJUnitPlatform()
}


tasks.withType<Javadoc>().configureEach {
    options.encoding = "UTF-8"
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            //artifactId = tasks.jar.get().archiveBaseName.get()
            artifactId ="nexalog-api"
        }
    }
}
