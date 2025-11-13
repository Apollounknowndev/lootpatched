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
    public static final List<String> LEVEL_TO_NAME = List.of("novice", "apprentice", "journeyman", "expert", "master");



    public static TradeOffer itemsForEmeralds(ItemLike selling, int emeraldCount, int sellingCount, int maxUses) {
        return itemsForEmeralds(selling, emeraldCount, sellingCount, maxUses, 1);
    }

    public static TradeOffer itemsForEmeralds(ItemLike selling, int emeraldCount, int sellingCount, int maxUses, int xp) {
        return itemsForEmeralds(new ItemStack(selling, sellingCount), emeraldCount, maxUses, xp);
    }

    public static TradeOffer itemsForEmeralds(ItemStack selling, int emeraldCount, int maxUses, int xp) {
        return new Base(new ItemCost(Items.EMERALD, emeraldCount), Optional.empty(), selling, Optional.empty(), maxUses, xp, 0.05F);
    }



    public static TradeOffer emeraldsForItems(ItemLike buying, int buyingCount, int maxUses, int xp) {
        return emeraldsForItems(new ItemCost(buying, buyingCount), 1, maxUses, xp);
    }

    public static TradeOffer emeraldsForItems(ItemLike buying, int buyingCount, int emeraldCount, int maxUses, int xp) {
        return emeraldsForItems(new ItemCost(buying, buyingCount), emeraldCount, maxUses, xp);
    }

    public static TradeOffer emeraldsForItems(ItemCost buying, int emeraldCount, int maxUses, int xp) {
        return new Base(buying, Optional.empty(), new ItemStack(Items.EMERALD, emeraldCount), Optional.empty(), maxUses, xp, 0.05F);
    }
}