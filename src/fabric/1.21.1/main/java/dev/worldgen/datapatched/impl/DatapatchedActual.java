package dev.worldgen.datapatched.impl;

import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceKey;
import net.msrandom.multiplatform.annotations.Actual;

public class DatapatchedActual {
    @Actual
    public static <T> Registry<T> registry(RegistryAccess registries, ResourceKey<? extends Registry<T>> key) {
        return registries.registryOrThrow(key);
    }
}
