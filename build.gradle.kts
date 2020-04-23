buildscript {
    repositories {
        maven("https://files.minecraftforge.net/maven")
        maven("https://repo.spongepowered.org/maven")
    }
    dependencies {
        classpath("org.spongepowered:mixingradle:0.7-SNAPSHOT")
    }
}

plugins {
    // Apply the java-library plugin to add support for Java Library
    `java-library`
    id("net.minecraftforge.gradle")
    id("net.minecrell.licenser") version "0.4"
}

defaultTasks("licenseFormat", "build")

apply {
    plugin("org.spongepowered.mixin")
}

minecraft {
    mappings("snapshot", "20200119-1.14.3")
    runs {
        create("server") {
            workingDirectory( project.file("../run"))
            main = "org.spongepowered.launch.Main"
        }
    }
}

repositories {
    mavenLocal()
    jcenter()
    maven("https://repo.spongepowered.org/maven")
    gradlePluginPortal()
}

license {
    header = file("HEADER.txt")
    newLine = false
    ext {
        this["name"] = project.name
        this["organization"] = "SpongePowered"
        this["url"] = "https://www.spongepowered.org"
    }

    include("**/*.java", "**/*.groovy", "**/*.kt")
}

dependencies {
    minecraft("net.minecraft:server:1.14.4")
    // This dependency is used internally, and not exposed to consumers on their own compile classpath.
    implementation("net.minecrell.licenser:net.minecrell.licenser.gradle.plugin:0.4.1")
    implementation("com.google.guava:guava:28.0-jre")
    implementation("cpw.mods:modlauncher:4.1.+")
    implementation("org.ow2.asm:asm-commons:6.2")
    implementation("cpw.mods:grossjava9hacks:1.1.+")
    implementation("org.spongepowered:mixin:0.8+")
    implementation("org.spongepowered:configurate-json:3.6.1")
    // Use JUnit test framework
    testImplementation("junit:junit:4.12")
}
