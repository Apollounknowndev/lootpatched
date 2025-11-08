package dev.worldgen.datapatched.impl;

import dev.worldgen.datapatched.api.loot.LootModifier;
import dev.worldgen.datapatched.api.DatapatchedBuiltInRegistries;
import dev.worldgen.datapatched.api.DatapatchedRegistries;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;

public class DatapatchedEntrypoint implements ModInitializer {
    @Override
    public void onInitialize() {
        DatapatchedBuiltInRegistries.init();
        DynamicRegistries.register(DatapatchedRegistries.LOOT_MODIFIER, LootModifier.CODEC);
        Datapatched.registerLootFunctions((name, type) -> Registry.register(BuiltInRegistries.LOOT_FUNCTION_TYPE, Datapatched.id(name), type));
        Datapatched.registerLootModifiers((name, codec) -> Registry.register(DatapatchedBuiltInRegistries.LOOT_MODIFIER_TYPE, Datapatched.id(name), codec));
    }
}
