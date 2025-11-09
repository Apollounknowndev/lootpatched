package dev.worldgen.datapatched.impl.loot.modifier;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.worldgen.datapatched.api.loot.LootModifier;
import java.util.List;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;

public record AddPools(CommonData commonData, List<LootPool> pools) implements LootModifier {
    public static final MapCodec<AddPools> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(CommonData.codec(1000).forGetter(AddPools::commonData), LootPool.CODEC.listOf().fieldOf("pools").forGetter(AddPools::pools)).apply(instance, AddPools::new));

    public AddPools(CommonData commonData, List<LootPool> pools) {
        this.commonData = commonData;
        this.pools = pools;
    }

    public void apply(LootTable table, ResourceLocation key) {
        List<LootPool> fullPools = ImmutableList.builder().addAll(this.accessor(table).datapatched$getPools()).addAll(this.pools).build();
        this.accessor(table).datapatched$setPools(fullPools);
    }

    public MapCodec<? extends LootModifier> codec() {
        return CODEC;
    }

    public CommonData commonData() {
        return this.commonData;
    }

    public List<LootPool> pools() {
        return this.pools;
    }
}
