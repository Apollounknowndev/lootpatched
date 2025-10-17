package dev.worldgen.lootpatched.impl.function;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.List;

public class RemoveItemFunction extends LootItemConditionalFunction {
    public static final MapCodec<RemoveItemFunction> CODEC = RecordCodecBuilder.mapCodec(instance -> commonFields(instance).apply(instance, RemoveItemFunction::new));
    public static final LootItemFunctionType<RemoveItemFunction> TYPE = new LootItemFunctionType<>(CODEC);

    protected RemoveItemFunction(List<LootItemCondition> conditions) {
        super(conditions);
    }

    @Override
    public LootItemFunctionType<RemoveItemFunction> getType() {
        return TYPE;
    }

    @Override
    public ItemStack run(ItemStack stack, LootContext context) {
        return ItemStack.EMPTY;
    }
}
