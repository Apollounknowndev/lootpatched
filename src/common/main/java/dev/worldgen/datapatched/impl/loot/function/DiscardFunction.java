package dev.worldgen.datapatched.impl.loot.function;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.List;

public class DiscardFunction extends LootItemConditionalFunction {
    public static final MapCodec<DiscardFunction> CODEC = RecordCodecBuilder.mapCodec(instance -> commonFields(instance).apply(instance, DiscardFunction::new));
    public static final LootItemFunctionType<DiscardFunction> TYPE = new LootItemFunctionType<>(CODEC);

    protected DiscardFunction(List<LootItemCondition> conditions) {
        super(conditions);
    }

    @Override
    public LootItemFunctionType<DiscardFunction> getType() {
        return TYPE;
    }

    @Override
    public ItemStack run(ItemStack stack, LootContext context) {
        return ItemStack.EMPTY;
    }
}
