plugins {
    id("java-library")
    id("maven-publish")
    id("io.freefair.lombok") version "6.4.1"
}

group = "tk.empee"
version = "1.0"

repositories {

    maven(url = "https://hub.spigotmc.org/nexus/content/repositories/snapshots/")

    mavenCentral()
    mavenLocal()
}

dependencies {

    compileOnly("org.spigotmc:spigot-api:1.18.1-R0.1-SNAPSHOT")

}

publishing {
    publications {
        register("mavenJava", MavenPublication::class) {
            artifactId = "recipesManager"
            from(components["java"])
        }
    }
}