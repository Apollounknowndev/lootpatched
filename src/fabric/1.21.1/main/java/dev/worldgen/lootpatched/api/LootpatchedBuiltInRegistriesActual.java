package dev.worldgen.lootpatched.api;

import com.mojang.serialization.MapCodec;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.core.Registry;
import net.msrandom.multiplatform.annotations.Actual;

public class LootpatchedBuiltInRegistriesActual {
    @Actual
    public static final Registry<MapCodec<? extends LootModifier>> LOOT_MODIFIER_TYPE = FabricRegistryBuilder.createSimple(LootpatchedRegistries.LOOT_MODIFIER_TYPE).buildAndRegister();
}
