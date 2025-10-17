package dev.worldgen.lootpatched.impl.modifier;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.worldgen.lootpatched.api.LootModifier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.List;

public record AddPools(CommonData commonData, List<LootPool> pools) implements LootModifier {
    public static final MapCodec<AddPools> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
        CommonData.codec(1000).forGetter(AddPools::commonData),
        LootPool.CODEC.listOf().fieldOf("pools").forGetter(AddPools::pools)
    ).apply(instance, AddPools::new));

    @Override
    public void apply(LootTable table, ResourceLocation key) {
        List<LootPool> fullPools = ImmutableList.<LootPool>builder().addAll(accessor(table).lootpatched$getPools()).addAll(this.pools).build();
        accessor(table).lootpatched$setPools(fullPools);
    }

    @Override
    public MapCodec<? extends LootModifier> codec() {
        return AddPools.CODEC;
    }
}
