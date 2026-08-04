plugins {
    id("java")
    id("dev.lumas.anvil") version "1.0.0"
    id("com.gradleup.shadow") version "9.0.2"
}

group = "me.arcaniax.hdb"
version = "4.24.0"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
    maven("https://repo.rosewooddev.io/repository/public/")
    maven("https://repo.extendedclip.com/releases/")
    maven("https://repo.opencollab.dev/main/")
    maven("https://repo.nexomc.com/releases")
    maven("https://hub.spigotmc.org/nexus/content/groups/public/")
    maven("https://libraries.minecraft.net/")
}

dependencies {
    compileOnly("com.github.MilkBowl:VaultAPI:1.7.1")
    compileOnly("org.black_ixx:playerpoints:3.3.5")
    compileOnly("me.clip:placeholderapi:2.12.3")
    compileOnly("org.geysermc.geyser:api:2.10.0-SNAPSHOT")
    compileOnly("com.nexomc:nexo:1.26.0")
    compileOnly("org.spigotmc:spigot-api:26.2-R0.1-SNAPSHOT")
    compileOnly("com.mojang:authlib:3.13.56")

    compileOnly("net.kyori:adventure-api:4.26.1")
    compileOnly("net.kyori:adventure-text-minimessage:4.26.1")

    implementation("net.kyori:adventure-platform-bukkit:4.4.1")
    implementation("org.bstats:bstats-bukkit:3.1.0")
}

anvil {
    vineflowerVersion = "1.12.0"
    inputJar = layout.projectDirectory.file("sources/HeadDatabase-4.24.0.jar")
    sourcePackage("me/arcaniax/hdb", ".")

    resource("texts", ".")
    resource("plugin.yml", ".")
    resource("config.yml", ".")
}

tasks.shadowJar {
    archiveClassifier.set("")
    val group = "me.arcaniax.hdb"
    relocate("net.kyori", "$group.libs.kyori")
    relocate("org.bstats", "$group.metrics")
    minimize()
}

tasks.build {
    dependsOn(tasks.shadowJar)
}