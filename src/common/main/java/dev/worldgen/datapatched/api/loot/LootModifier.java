package dev.worldgen.datapatched.api.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import dev.worldgen.datapatched.api.DatapatchedBuiltInRegistries;
import dev.worldgen.datapatched.mixin.loot.LootTableAccessor;
import dev.worldgen.datapatched.impl.loot.modifier.CommonData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.function.Function;

public interface LootModifier {
    Codec<LootModifier> CODEC = DatapatchedBuiltInRegistries.LOOT_MODIFIER_TYPE.byNameCodec().dispatch(LootModifier::codec, Function.identity());

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
