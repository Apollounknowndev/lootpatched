package dev.worldgen.lootpatched.impl;

import com.mojang.serialization.MapCodec;
import dev.worldgen.lootpatched.api.LootModifier;
import dev.worldgen.lootpatched.api.LootpatchedBuiltInRegistries;
import dev.worldgen.lootpatched.api.LootpatchedRegistries;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.RegisterEvent;

@Mod(Lootpatched.MOD_ID)
public class LootpatchedEntrypoint {
    public static final DeferredRegister<MapCodec<? extends LootModifier>> DEFERRED_LOOT_MODIFIER_TYPES = DeferredRegister.create(LootpatchedRegistries.LOOT_MODIFIER_TYPE, Lootpatched.MOD_ID);

    public LootpatchedEntrypoint(IEventBus bus) {
        LootpatchedBuiltInRegistries.init();

        bus.addListener((DataPackRegistryEvent.NewRegistry event) -> event.dataPackRegistry(LootpatchedRegistries.LOOT_MODIFIER, LootModifier.CODEC));

        bus.addListener((RegisterEvent event) -> Lootpatched.registerLootFunctions((name, type) -> register(event, Registries.LOOT_FUNCTION_TYPE, name, type)));

        Lootpatched.registerLootModifiers((name, codec) -> DEFERRED_LOOT_MODIFIER_TYPES.register(name, () -> codec));
        DEFERRED_LOOT_MODIFIER_TYPES.register(bus);
    }

    private static <T> void register(RegisterEvent event, ResourceKey<Registry<T>> registry, String name, T object) {
        event.register(registry, helper -> helper.register(Lootpatched.key(registry, name), object));
    }
}
