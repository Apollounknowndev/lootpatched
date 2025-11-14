package dev.worldgen.datapatched.data.base.generator.offer;

import dev.worldgen.datapatched.api.trade.TradeOffer;
import dev.worldgen.datapatched.api.trade.TradeOfferBuilder;
import dev.worldgen.datapatched.data.base.generator.BaseTradeOfferBootstrap;
import dev.worldgen.datapatched.impl.VillagerKeys;
import dev.worldgen.datapatched.impl.trade.offer.EnchantedItem;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;

import java.util.ArrayList;
import java.util.List;

import static dev.worldgen.datapatched.data.base.generator.BaseTradeOfferBootstrap.register;

public class FishermanOffers {
    public static final List<List<ResourceKey<TradeOffer>>> OFFERS = new ArrayList<>();

    public static void bootstrap(BootstrapContext<TradeOffer> context) {
        OFFERS.add(List.of(
            register(context, name(1, "buy_string"), TradeOfferBuilder.emeraldsForItems(Items.STRING, 20, 16, 2)),
            register(context, name(1, "buy_coal"), TradeOfferBuilder.emeraldsForItems(Items.COAL, 10, 16, 2)),
            register(context, name(1, "cook_cod"), TradeOfferBuilder.itemsAndEmeraldsForItems(Items.COD, 6, 1, Items.COOKED_COD, 16, 1)),
            register(context, name(1, "sell_cod_bucket"), TradeOfferBuilder.itemsForEmeralds(Items.COD_BUCKET, 3, 1, 16, 1))
        ));
        OFFERS.add(List.of(
            register(context, name(2, "buy_cod"), TradeOfferBuilder.emeraldsForItems(Items.COD, 15, 16, 10)),
            register(context, name(2, "cook_salmon"), TradeOfferBuilder.itemsAndEmeraldsForItems(Items.COD, 6, 1, Items.COOKED_COD, 16, 5)),
            register(context, name(2, "sell_campfire"), TradeOfferBuilder.itemsForEmeralds(Items.CAMPFIRE, 2, 1, 16, 5))
        ));
        OFFERS.add(List.of(
            register(context, name(3, "buy_salmon"), TradeOfferBuilder.emeraldsForItems(Items.SALMON, 13, 16, 10)),
            register(context, name(3, "buy_enchanted_fishing_rod"), TradeOfferBuilder.enchantedItem(Items.FISHING_ROD, 3, 3, 10, 0.2f))
        ));
        OFFERS.add(List.of(
            register(context, name(4, "buy_tropical_fish"), TradeOfferBuilder.emeraldsForItems(Items.TROPICAL_FISH, 6, 12, 30))
        ));
        OFFERS.add(List.of(
            register(context, name(5, "buy_pufferfish"), TradeOfferBuilder.emeraldsForItems(Items.PUFFERFISH, 4, 12, 30)),
            boat(context, Items.OAK_BOAT, VillagerKeys.PLAINS),
            boat(context, Items.SPRUCE_BOAT,  VillagerKeys.TAIGA, VillagerKeys.SNOW),
            boat(context, Items.JUNGLE_BOAT, VillagerKeys.DESERT, VillagerKeys.JUNGLE),
            boat(context, Items.ACACIA_BOAT, VillagerKeys.SAVANNA),
            boat(context, Items.DARK_OAK_BOAT, VillagerKeys.SWAMP)
        ));
    }

    @SafeVarargs
    private static ResourceKey<TradeOffer> boat(BootstrapContext<TradeOffer> context, Item item, ResourceKey<VillagerType>... types) {
        return register(
            context,
            name(5, "sell_" + BaseTradeOfferBootstrap.itemPath(item)),
            TradeOfferBuilder.typeSpecific(TradeOfferBuilder.emeraldsForItems(item, 1, 12, 30), types)
        );
    }

    private static String name(int level, String name) {
        return String.format("fisherman/%s/%s", TradeOfferBuilder.LEVEL_TO_NAME.get(level - 1), name);
    }
}
