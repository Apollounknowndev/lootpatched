package dev.worldgen.datapatched.impl;

import com.mojang.serialization.MapCodec;
import dev.worldgen.datapatched.api.loot.LootModifier;
import dev.worldgen.datapatched.api.DatapatchedBuiltInRegistries;
import dev.worldgen.datapatched.api.DatapatchedRegistries;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.RegisterEvent;

@Mod(Datapatched.MOD_ID)
public class DatapatchedEntrypoint {
    public static final DeferredRegister<MapCodec<? extends LootModifier>> DEFERRED_LOOT_MODIFIER_TYPES = DeferredRegister.create(DatapatchedRegistries.LOOT_MODIFIER_TYPE, Datapatched.MOD_ID);

    public DatapatchedEntrypoint(IEventBus bus) {
        DatapatchedBuiltInRegistries.init();

        bus.addListener((DataPackRegistryEvent.NewRegistry event) -> event.dataPackRegistry(DatapatchedRegistries.LOOT_MODIFIER, LootModifier.CODEC));

        bus.addListener((RegisterEvent event) -> Datapatched.registerLootFunctions((name, type) -> register(event, Registries.LOOT_FUNCTION_TYPE, name, type)));

        Datapatched.registerLootModifiers((name, codec) -> DEFERRED_LOOT_MODIFIER_TYPES.register(name, () -> codec));
        DEFERRED_LOOT_MODIFIER_TYPES.register(bus);
    }

    private static <T> void register(RegisterEvent event, ResourceKey<Registry<T>> registry, String name, T object) {
        event.register(registry, helper -> helper.register(Datapatched.key(registry, name), object));
    }
}
