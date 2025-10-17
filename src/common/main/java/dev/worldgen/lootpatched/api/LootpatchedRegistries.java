package dev.worldgen.lootpatched.api;

import com.mojang.serialization.MapCodec;
import dev.worldgen.lootpatched.impl.Lootpatched;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public class LootpatchedRegistries {
    public static final ResourceKey<Registry<LootModifier>> LOOT_MODIFIER = key("loot_modifier");
    public static final ResourceKey<Registry<MapCodec<? extends LootModifier>>> LOOT_MODIFIER_TYPE = key("loot_modifier_type");

    private static <T> ResourceKey<Registry<T>> key(String name) {
        return ResourceKey.createRegistryKey(Lootpatched.id(name));
    }
}
