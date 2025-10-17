package dev.worldgen.lootpatched.api;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.msrandom.multiplatform.annotations.Expect;

public class LootpatchedBuiltInRegistries {
    @Expect
    public static final Registry<MapCodec<? extends LootModifier>> LOOT_MODIFIER_TYPE;

    public static void init() {

    }
}
