package dev.worldgen.lootpatched.api;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import dev.worldgen.lootpatched.impl.mixin.LootTableAccessor;
import dev.worldgen.lootpatched.impl.modifier.CommonData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.function.Function;

public interface LootModifier {
    Codec<LootModifier> CODEC = LootpatchedBuiltInRegistries.LOOT_MODIFIER_TYPE.byNameCodec().dispatch(LootModifier::codec, Function.identity());

    CommonData commonData();

    void apply(LootTable table, ResourceLocation id);

    MapCodec<? extends LootModifier> codec();

    default boolean tryApply(LootTable table, ResourceLocation id) {
        if (this.commonData().target().test(id)) {
            apply(table, id);
            return true;
        }
        return false;
    }

    default LootTableAccessor accessor(LootTable table) {
        return (LootTableAccessor) table;
    }
}
