package dev.worldgen.datapatched.api.trade;

import dev.worldgen.datapatched.impl.trade.offer.Base;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.level.ItemLike;

public class TradeOfferBuilder {
    public static final List<String> LEVEL_TO_NAME = List.of("N/A", "novice", "apprentice", "journeyman", "expert", "master");

    public static TradeOffer itemsForEmeralds(ItemLike selling, int buyingCount, int sellingCount, int maxUses) {
        return itemsForEmeralds(selling, buyingCount, sellingCount, maxUses, 1);
    }

    public static TradeOffer itemsForEmeralds(ItemLike selling, int buyingCount, int sellingCount, int maxUses, int xp) {
        return itemsForEmeralds(new ItemStack(selling, sellingCount), buyingCount, maxUses, xp);
    }

    public static TradeOffer itemsForEmeralds(ItemStack selling, int buyingCount, int maxUses, int xp) {
        return new Base(new ItemCost(Items.EMERALD, buyingCount), Optional.empty(), selling, Optional.empty(), maxUses, xp, 0.05F);
    }

    public static TradeOffer emeraldsForItems(ItemLike buying, int buyingCount, int sellingCount, int maxUses) {
        return emeraldsForItems(buying, buyingCount, sellingCount, maxUses, 1);
    }

    public static TradeOffer emeraldsForItems(ItemLike buying, int buyingCount, int sellingCount, int maxUses, int xp) {
        return emeraldsForItems(new ItemCost(buying, sellingCount), buyingCount, maxUses, xp);
    }

    public static TradeOffer emeraldsForItems(ItemCost buying, int sellingCount, int maxUses, int xp) {
        return new Base(buying, Optional.empty(), new ItemStack(Items.EMERALD, sellingCount), Optional.empty(), maxUses, xp, 0.05F);
    }
}