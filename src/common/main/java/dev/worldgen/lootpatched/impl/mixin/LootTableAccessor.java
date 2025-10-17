package dev.worldgen.lootpatched.impl.mixin;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;
import java.util.function.BiFunction;

@Mixin(LootTable.class)
public interface LootTableAccessor {
    @Accessor("pools") @Mutable
    List<LootPool> lootpatched$getPools();

    @Accessor("pools") @Mutable
    void lootpatched$setPools(List<LootPool> pools);

    @Accessor("functions") @Mutable
    List<LootItemFunction> lootpatched$getFunctions();

    @Accessor("functions") @Mutable
    void lootpatched$setFunctions(List<LootItemFunction> pools);

    @Accessor("compositeFunction") @Mutable
    void lootpatched$setCompositeFunction(BiFunction<ItemStack, LootContext, ItemStack> compositeFunction);
}
