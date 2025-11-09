package dev.worldgen.datapatched.impl.trade.offer;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.worldgen.datapatched.api.trade.SimpleTradeOffer;
import dev.worldgen.datapatched.api.trade.TradeOffer;
import java.util.Optional;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.util.InclusiveRange;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctions;

public record EnchantedItem(ItemCost buying, Optional<ItemCost> buyingSecondary, ItemStack selling, Optional<LootItemFunction> sellingModifier, InclusiveRange<Integer> levels, int maxUses, int experience, float priceMultiplier) implements SimpleTradeOffer {
    public static final MapCodec<EnchantedItem> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(BUYING_CODEC.fieldOf("buying").forGetter(EnchantedItem::buying), BUYING_CODEC.optionalFieldOf("buying_secondary").forGetter(EnchantedItem::buyingSecondary), ItemStack.SIMPLE_ITEM_CODEC.fieldOf("selling").forGetter(EnchantedItem::selling), LootItemFunctions.ROOT_CODEC.optionalFieldOf("selling_modifier").forGetter(EnchantedItem::sellingModifier), InclusiveRange.INT.fieldOf("levels").orElse(new InclusiveRange(5, 19)).forGetter(EnchantedItem::levels), Codec.INT.fieldOf("max_uses").orElse(12).forGetter(EnchantedItem::maxUses), Codec.INT.fieldOf("experience").orElse(1).forGetter(EnchantedItem::experience), Codec.FLOAT.fieldOf("price_multiplier").orElse(0.05F).forGetter(EnchantedItem::priceMultiplier)).apply(instance, EnchantedItem::new));

    public EnchantedItem(ItemCost buying, Optional<ItemCost> buyingSecondary, ItemStack selling, Optional<LootItemFunction> sellingModifier, InclusiveRange<Integer> levels, int maxUses, int experience, float priceMultiplier) {
        this.buying = buying;
        this.buyingSecondary = buyingSecondary;
        this.selling = selling;
        this.sellingModifier = sellingModifier;
        this.levels = levels;
        this.maxUses = maxUses;
        this.experience = experience;
        this.priceMultiplier = priceMultiplier;
    }

    public SimpleTradeOffer.ItemTrade createItemTrade(AbstractVillager entity, RandomSource random) {
        int level = random.nextIntBetweenInclusive((Integer)this.levels.minInclusive(), (Integer)this.levels.maxInclusive());
        RegistryAccess registries = entity.registryAccess();
        Optional<HolderSet.Named<Enchantment>> optional = registries.lookupOrThrow(Registries.ENCHANTMENT).get(EnchantmentTags.ON_TRADED_EQUIPMENT);
        ItemCost finalBuying = new ItemCost((ItemLike)this.buying.item().value(), this.buying.count() + level);
        ItemStack finalSelling = EnchantmentHelper.enchantItem(random, this.selling.copy(), level, registries, optional);
        return new SimpleTradeOffer.ItemTrade(finalBuying, this.buyingSecondary, this.modifyStack(entity, finalSelling));
    }

    private ItemStack modifyStack(AbstractVillager entity, ItemStack original) {
        if (this.sellingModifier.isEmpty()) {
            return original;
        } else {
            LootContext context = TradeOffer.createContext(entity);
            ItemStack modified = (ItemStack)((LootItemFunction)this.sellingModifier.get()).apply(original, context);
            modified.limitSize(modified.getMaxStackSize());
            return modified;
        }
    }

    public MapCodec<? extends TradeOffer> codec() {
        return CODEC;
    }

    public ItemCost buying() {
        return this.buying;
    }

    public Optional<ItemCost> buyingSecondary() {
        return this.buyingSecondary;
    }

    public ItemStack selling() {
        return this.selling;
    }

    public Optional<LootItemFunction> sellingModifier() {
        return this.sellingModifier;
    }

    public InclusiveRange<Integer> levels() {
        return this.levels;
    }

    public int maxUses() {
        return this.maxUses;
    }

    public int experience() {
        return this.experience;
    }

    public float priceMultiplier() {
        return this.priceMultiplier;
    }
}
