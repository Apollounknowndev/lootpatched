package dev.worldgen.lootpatched.impl;

import dev.worldgen.lootpatched.api.LootModifier;
import dev.worldgen.lootpatched.api.LootpatchedBuiltInRegistries;
import dev.worldgen.lootpatched.api.LootpatchedRegistries;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;

public class LootpatchedEntrypoint implements ModInitializer {
    @Override
    public void onInitialize() {
        LootpatchedBuiltInRegistries.init();
        DynamicRegistries.register(LootpatchedRegistries.LOOT_MODIFIER, LootModifier.CODEC);
        Lootpatched.registerLootFunctions((name, type) -> Registry.register(BuiltInRegistries.LOOT_FUNCTION_TYPE, Lootpatched.id(name), type));
        Lootpatched.registerLootModifiers((name, codec) -> Registry.register(LootpatchedBuiltInRegistries.LOOT_MODIFIER_TYPE, Lootpatched.id(name), codec));
    }
}
