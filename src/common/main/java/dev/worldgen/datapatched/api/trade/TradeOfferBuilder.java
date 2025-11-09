package dev.worldgen.datapatched.api.trade;

import dev.worldgen.datapatched.impl.trade.offer.Base;
import java.util.Optional;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.level.ItemLike;

public class TradeOfferBuilder {
    public static TradeOffer itemsForEmeralds(ItemLike selling, int buyingCount, int sellingCount, int maxUses) {
        return itemsForEmeralds(new ItemStack(selling, sellingCount), buyingCount, sellingCount, maxUses);
    }

    public static TradeOffer itemsForEmeralds(ItemStack selling, int buyingCount, int sellingCount, int maxUses) {
        return new Base(new ItemCost(Items.EMERALD, buyingCount), Optional.empty(), selling.copyWithCount(sellingCount), Optional.empty(), maxUses, 1, 0.0F);
    }
}