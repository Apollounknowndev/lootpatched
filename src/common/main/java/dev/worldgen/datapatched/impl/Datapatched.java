package dev.worldgen.datapatched.impl;

import com.mojang.serialization.MapCodec;
import dev.worldgen.datapatched.api.loot.LootModifier;
import dev.worldgen.datapatched.impl.loot.function.ItemSwapFunction;
import dev.worldgen.datapatched.impl.loot.function.DiscardFunction;
import dev.worldgen.datapatched.impl.loot.modifier.AddEntries;
import dev.worldgen.datapatched.impl.loot.modifier.AddPools;
import dev.worldgen.datapatched.impl.loot.modifier.ApplyFunction;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.BiConsumer;

public class Datapatched {
    public static final String MOD_ID = "datapatched";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static ResourceLocation id(String name) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, name);
    }

    public static <T> ResourceKey<T> key(ResourceKey<? extends Registry<T>> resourceKey, String name) {
        return ResourceKey.create(resourceKey, id(name));
    }

    public static void registerLootFunctions(BiConsumer<String, LootItemFunctionType<?>> consumer) {
        consumer.accept("item_swap", ItemSwapFunction.TYPE);
        consumer.accept("discard", DiscardFunction.TYPE);
    }

    public static void registerLootModifiers(BiConsumer<String, MapCodec<? extends LootModifier>> consumer) {
        consumer.accept("add_entries", AddEntries.CODEC);
        consumer.accept("add_pools", AddPools.CODEC);
        consumer.accept("apply_function", ApplyFunction.CODEC);
    }
}
