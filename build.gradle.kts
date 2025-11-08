plugins {
    kotlin("jvm") version "2.1.0"
    id("earth.terrarium.cloche") version "0.13.6"
}

repositories {
    cloche {
        mavenNeoforgedMeta()
        mavenNeoforged()
        mavenForge()
        mavenFabric()
        mavenParchment()
        librariesMinecraft()
        main()
    }
    mavenLocal()
    mavenCentral()
    maven("https://api.modrinth.com/maven")
}

group = "dev.worldgen"
version = "1.0.0"

cloche {
    mappings {
        official()
    }

    metadata {
        modId = "datapatched"
        name = "Datapatched"
        description = "A library mod with a simple cross-loader loot modifier format."
        license = "MIT"
        icon = "pack.png"

        author("Apollo")
    }

    common {
        mixins.from(file("src/common/main/datapatched.mixins.json"))

        dependencies {
            compileOnly("org.spongepowered:mixin:0.8.3")
        }
    }

    fabric("fabric:1.21.1") {
        loaderVersion = "0.17.0"
        minecraftVersion = "1.21.1"

        dependencies {
            fabricApi("0.116.1")
        }

        includedClient()
        runs {
            client()
            server()
        }

        metadata {
            entrypoint("main") {
                value = "dev.worldgen.datapatched.impl.DatapatchedEntrypoint"
            }
        }
    }

    fabric("fabric:1.21.10") {
        loaderVersion = "0.17.2"
        minecraftVersion = "1.21.9"

        dependencies {
            fabricApi("0.134.0")
        }

        includedClient()
        runs {
            client()
            server()
        }

        metadata {
            entrypoint("main") {
                value = "dev.worldgen.datapatched.impl.DatapatchedEntrypoint"
            }
        }
    }

    neoforge("neoforge:1.21.1") {
        loaderVersion = "21.1.206"
        minecraftVersion = "1.21.1"

        runs {
            client()
            server()
        }
    }

    neoforge("neoforge:1.21.10") {
        loaderVersion = "21.9.1-beta"
        minecraftVersion = "1.21.9"

        runs {
            client()
            server()
        }
    }
}