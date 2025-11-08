package dev.worldgen.datapatched.api;

import com.mojang.serialization.MapCodec;
import dev.worldgen.datapatched.api.loot.LootModifier;
import dev.worldgen.datapatched.impl.Datapatched;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public class DatapatchedRegistries {
    public static final ResourceKey<Registry<LootModifier>> LOOT_MODIFIER = key("loot_modifier");
    public static final ResourceKey<Registry<MapCodec<? extends LootModifier>>> LOOT_MODIFIER_TYPE = key("loot_modifier_type");

    private static <T> ResourceKey<Registry<T>> key(String name) {
        return ResourceKey.createRegistryKey(Datapatched.id(name));
    }
}
