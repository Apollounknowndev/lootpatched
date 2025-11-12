package dev.worldgen.datapatched.data.base.generator.offer;

import dev.worldgen.datapatched.api.trade.TradeOffer;
import dev.worldgen.datapatched.api.trade.TradeOfferBuilder;
import dev.worldgen.datapatched.data.base.generator.BaseTradeOfferBootstrap;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static dev.worldgen.datapatched.data.base.generator.BaseTradeOfferBootstrap.register;

public class FarmerOffers {
    public static final Map<String, List<ResourceKey<TradeOffer>>> OFFERS = new HashMap<>();

    public static void bootstrap(BootstrapContext<TradeOffer> context) {
        register(context, name(1, "buy_wheat"), TradeOfferBuilder.itemsForEmeralds(Items.BREAD, 1, 6, 16, 1));
    }

    private static String name(int level, String name) {
        return String.format("farmer/%s/%s", TradeOfferBuilder.LEVEL_TO_NAME.get(level), name);
    }

    private static void normal(BootstrapContext<TradeOffer> context, ItemLike item, int price, int count, int maxUses) {
        var key = BaseTradeOfferBootstrap.key("wandering_trader/normal/buy_" + BaseTradeOfferBootstrap.itemPath(item));
        context.register(key, TradeOfferBuilder.itemsForEmeralds(item, price, count, maxUses));
        //NORMAL_OFFERS.add(key);
    }

    private static void special(BootstrapContext<TradeOffer> context, ItemLike item, int price, int count, int maxUses) {
        var key = BaseTradeOfferBootstrap.key("wandering_trader/special/buy_" + BaseTradeOfferBootstrap.itemPath(item));
        context.register(key, TradeOfferBuilder.itemsForEmeralds(item, price, count, maxUses));
        //SPECIAL_OFFERS.add(key);
    }
}
