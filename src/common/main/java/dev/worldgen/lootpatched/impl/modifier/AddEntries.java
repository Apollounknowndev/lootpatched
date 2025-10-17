package dev.worldgen.lootpatched.impl.modifier;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.worldgen.lootpatched.api.LootModifier;
import dev.worldgen.lootpatched.impl.mixin.LootPoolAccessor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntries;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;

import java.util.List;

public record AddEntries(CommonData commonData, List<LootPoolEntryContainer> entries) implements LootModifier {
    public static final MapCodec<AddEntries> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
        CommonData.codec(0).forGetter(AddEntries::commonData),
        LootPoolEntries.CODEC.listOf().fieldOf("entries").forGetter(AddEntries::entries)
    ).apply(instance, AddEntries::new));

    @Override
    public void apply(LootTable table, ResourceLocation key) {
        List<LootPool> pools = accessor(table).lootpatched$getPools();
        for (LootPool pool : pools) {
            var baseEntries = ImmutableList.<LootPoolEntryContainer>builder().addAll(accessor(pool).lootpatched$getEntries()).addAll(this.entries).build();
            accessor(pool).lootpatched$setEntries(baseEntries);
        }
    }

    private static LootPoolAccessor accessor(LootPool pool) {
        return (LootPoolAccessor) pool;
    }

    @Override
    public MapCodec<? extends LootModifier> codec() {
        return AddEntries.CODEC;
    }
}
