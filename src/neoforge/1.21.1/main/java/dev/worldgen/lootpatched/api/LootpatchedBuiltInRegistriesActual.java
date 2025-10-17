package dev.worldgen.lootpatched.api;

import com.mojang.serialization.MapCodec;
import dev.worldgen.lootpatched.impl.LootpatchedEntrypoint;
import net.minecraft.core.Registry;
import net.msrandom.multiplatform.annotations.Actual;

public class LootpatchedBuiltInRegistriesActual {
    @Actual
    public static final Registry<MapCodec<? extends LootModifier>> LOOT_MODIFIER_TYPE = LootpatchedEntrypoint.DEFERRED_LOOT_MODIFIER_TYPES.makeRegistry(b -> {});
}
