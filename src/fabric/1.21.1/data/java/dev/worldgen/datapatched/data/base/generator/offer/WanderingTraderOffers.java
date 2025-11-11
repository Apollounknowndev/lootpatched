package dev.worldgen.datapatched.data.base.generator.offer;

import dev.worldgen.datapatched.api.trade.TradeOffer;
import dev.worldgen.datapatched.api.trade.TradeOfferBuilder;
import dev.worldgen.datapatched.data.base.generator.BaseTradeOfferBootstrap;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import java.util.ArrayList;
import java.util.List;

public class WanderingTraderOffers {
    public static final List<ResourceKey<TradeOffer>> NORMAL_OFFERS = new ArrayList<>();
    public static final List<ResourceKey<TradeOffer>> SPECIAL_OFFERS = new ArrayList<>();

    public static void bootstrap(BootstrapContext<TradeOffer> context) {
        normal(context, Items.SEA_PICKLE, 2, 1, 5);
        normal(context, Items.SLIME_BALL, 4, 1, 5);
        normal(context, Items.GLOWSTONE, 2, 1, 5);
        normal(context, Items.NAUTILUS_SHELL, 5, 1, 5);
        normal(context, Items.FERN, 1, 1, 12);
        normal(context, Items.SUGAR_CANE, 1, 1, 8);
        normal(context, Items.PUMPKIN, 1, 1, 4);
        normal(context, Items.KELP, 3, 1, 12);
        normal(context, Items.CACTUS, 3, 1, 8);
        normal(context, Items.DANDELION, 1, 1, 12);
        normal(context, Items.POPPY, 1, 1, 12);
        normal(context, Items.BLUE_ORCHID, 1, 1, 8);
        normal(context, Items.ALLIUM, 1, 1, 12);
        normal(context, Items.AZURE_BLUET, 1, 1, 12);
        normal(context, Items.RED_TULIP, 1, 1, 12);
        normal(context, Items.ORANGE_TULIP, 1, 1, 12);
        normal(context, Items.WHITE_TULIP, 1, 1, 12);
        normal(context, Items.PINK_TULIP, 1, 1, 12);
        normal(context, Items.OXEYE_DAISY, 1, 1, 12);
        normal(context, Items.CORNFLOWER, 1, 1, 12);
        normal(context, Items.LILY_OF_THE_VALLEY, 1, 1, 7);
        normal(context, Items.WHEAT_SEEDS, 1, 1, 12);
        normal(context, Items.BEETROOT_SEEDS, 1, 1, 12);
        normal(context, Items.PUMPKIN_SEEDS, 1, 1, 12);
        normal(context, Items.MELON_SEEDS, 1, 1, 12);
        normal(context, Items.ACACIA_SAPLING, 5, 1, 8);
        normal(context, Items.BIRCH_SAPLING, 5, 1, 8);
        normal(context, Items.DARK_OAK_SAPLING, 5, 1, 8);
        normal(context, Items.JUNGLE_SAPLING, 5, 1, 8);
        normal(context, Items.OAK_SAPLING, 5, 1, 8);
        normal(context, Items.SPRUCE_SAPLING, 5, 1, 8);
        normal(context, Items.CHERRY_SAPLING, 5, 1, 8);
        normal(context, Items.MANGROVE_PROPAGULE, 5, 1, 8);
        normal(context, Items.RED_DYE, 1, 3, 12);
        normal(context, Items.WHITE_DYE, 1, 3, 12);
        normal(context, Items.BLUE_DYE, 1, 3, 12);
        normal(context, Items.PINK_DYE, 1, 3, 12);
        normal(context, Items.BLACK_DYE, 1, 3, 12);
        normal(context, Items.GREEN_DYE, 1, 3, 12);
        normal(context, Items.LIGHT_GRAY_DYE, 1, 3, 12);
        normal(context, Items.MAGENTA_DYE, 1, 3, 12);
        normal(context, Items.YELLOW_DYE, 1, 3, 12);
        normal(context, Items.GRAY_DYE, 1, 3, 12);
        normal(context, Items.PURPLE_DYE, 1, 3, 12);
        normal(context, Items.LIGHT_BLUE_DYE, 1, 3, 12);
        normal(context, Items.LIME_DYE, 1, 3, 12);
        normal(context, Items.ORANGE_DYE, 1, 3, 12);
        normal(context, Items.BROWN_DYE, 1, 3, 12);
        normal(context, Items.CYAN_DYE, 1, 3, 12);
        normal(context, Items.BRAIN_CORAL_BLOCK, 3, 1, 8);
        normal(context, Items.BUBBLE_CORAL_BLOCK, 3, 1, 8);
        normal(context, Items.FIRE_CORAL_BLOCK, 3, 1, 8);
        normal(context, Items.HORN_CORAL_BLOCK, 3, 1, 8);
        normal(context, Items.TUBE_CORAL_BLOCK, 3, 1, 8);
        normal(context, Items.VINE, 1, 1, 12); // Sells 3 in trade rebalance
        normal(context, Items.BROWN_MUSHROOM, 1, 1, 12); // Sells 3 in trade rebalance
        normal(context, Items.RED_MUSHROOM, 1, 1, 12); // Sells 3 in trade rebalance
        normal(context, Items.LILY_PAD, 1, 2, 5); // Sells 5 in trade rebalance
        normal(context, Items.SMALL_DRIPLEAF, 1, 2, 5);
        normal(context, Items.SAND, 1, 8, 8);
        normal(context, Items.RED_SAND, 1, 4, 6);
        normal(context, Items.POINTED_DRIPSTONE, 1, 2, 5);
        normal(context, Items.ROOTED_DIRT, 1, 2, 5);
        normal(context, Items.MOSS_BLOCK, 1, 2, 5);
        
        special(context, Items.TROPICAL_FISH_BUCKET, 5, 1, 4); // Costs 3 in trade rebalance, in normal instead
        special(context, Items.PUFFERFISH_BUCKET, 5, 1, 4); // Costs 3 in trade rebalance, in normal instead
        special(context, Items.PACKED_ICE, 3, 1, 6); // Costs 1 in trade rebalance
        special(context, Items.BLUE_ICE, 6, 1, 6);
        special(context, Items.GUNPOWDER, 1, 1, 8); // Sells 4 in trade rebalance
        special(context, Items.PODZOL, 3, 3, 6);
    }

    private static void normal(BootstrapContext<TradeOffer> context, ItemLike item, int price, int count, int maxUses) {
        var key = BaseTradeOfferBootstrap.key("wandering_trader/normal/buy_" + BaseTradeOfferBootstrap.itemPath(item));
        context.register(key, TradeOfferBuilder.itemsForEmeralds(item, price, count, maxUses));
        NORMAL_OFFERS.add(key);
    }

    private static void special(BootstrapContext<TradeOffer> context, ItemLike item, int price, int count, int maxUses) {
        var key = BaseTradeOfferBootstrap.key("wandering_trader/special/buy_" + BaseTradeOfferBootstrap.itemPath(item));
        context.register(key, TradeOfferBuilder.itemsForEmeralds(item, price, count, maxUses));
        SPECIAL_OFFERS.add(key);
    }
}
