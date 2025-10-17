package dev.worldgen.lootpatched.impl.modifier;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.worldgen.lootpatched.api.LootModifier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctions;

import java.util.List;

public record ApplyFunction(CommonData commonData, LootItemFunction function) implements LootModifier {
    public static final MapCodec<ApplyFunction> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
        CommonData.codec(2000).forGetter(ApplyFunction::commonData),
        LootItemFunctions.ROOT_CODEC.fieldOf("function").forGetter(ApplyFunction::function)
    ).apply(instance, ApplyFunction::new));

    @Override
    public void apply(LootTable table, ResourceLocation key) {
        var accessor = accessor(table);
        List<LootItemFunction> fullFunctions = ImmutableList.<LootItemFunction>builder().addAll(accessor.lootpatched$getFunctions()).add(this.function).build();
        accessor.lootpatched$setFunctions(fullFunctions);
        accessor.lootpatched$setCompositeFunction(LootItemFunctions.compose(fullFunctions));
    }

    @Override
    public MapCodec<? extends LootModifier> codec() {
        return ApplyFunction.CODEC;
    }
}
