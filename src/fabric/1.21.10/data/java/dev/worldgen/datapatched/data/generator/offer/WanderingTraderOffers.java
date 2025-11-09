package dev.worldgen.datapatched.data.generator.offer;

import dev.worldgen.datapatched.api.trade.TradeOffer;
import dev.worldgen.datapatched.api.trade.TradeOfferBuilder;
import dev.worldgen.datapatched.data.generator.TradeOfferDatagen;
import dev.worldgen.datapatched.impl.trade.offer.Empty;
import net.minecraft.client.color.item.Potion;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.ItemLike;

import java.util.ArrayList;
import java.util.List;

import static dev.worldgen.datapatched.data.generator.TradeOfferDatagen.key;

public class WanderingTraderOffers {
    public static final List<ResourceKey<TradeOffer>> NORMAL_OVERLAY_OFFERS = new ArrayList<>();
    public static final List<ResourceKey<TradeOffer>> SPECIAL_OVERLAY_OFFERS = new ArrayList<>();

    public static void bootstrap(BootstrapContext<TradeOffer> context) {
        normal(context, Items.VINE, 1, 3, 12, false);
        normal(context, Items.BROWN_MUSHROOM, 1, 3, 12, false);
        normal(context, Items.RED_MUSHROOM, 1, 3, 12, false);
        normal(context, Items.LILY_PAD, 1, 5, 5, false);
        normal(context, Items.TROPICAL_FISH_BUCKET, 3, 1, 4, true);
        normal(context, Items.PUFFERFISH_BUCKET, 3, 1, 4, true);

        context.register(key("wandering_trader/special/buy_tropical_fish_bucket"), new Empty());
        context.register(key("wandering_trader/special/buy_pufferfish_bucket"), new Empty());
        special(context, Items.PACKED_ICE, 1, 1, 6, false);
        special(context, Items.GUNPOWDER, 1, 4, 8, false);
        special(context, Items.ACACIA_LOG, 1, 8, 4, true);
        special(context, Items.BIRCH_LOG, 1, 8, 4, true);
        special(context, Items.CHERRY_LOG, 1, 8, 4, true);
        special(context, Items.DARK_OAK_LOG, 1, 8, 4, true);
        special(context, Items.JUNGLE_LOG, 1, 8, 4, true);
        special(context, Items.MANGROVE_LOG, 1, 8, 4, true);
        special(context, Items.OAK_LOG, 1, 8, 4, true);
        special(context, Items.PALE_OAK_LOG, 1, 8, 4, true);
        special(context, Items.SPRUCE_LOG, 1, 8, 4, true);
        special(context, PotionContents.createItemStack(Items.POTION, Potions.LONG_INVISIBILITY), 5, 1, 1, true);
    }

    private static void normal(BootstrapContext<TradeOffer> context, ItemLike item, int buyingCount, int sellingCount, int maxUses, boolean addToTag) {
        var key = key("wandering_trader/normal/buy_" + TradeOfferDatagen.itemPath(item));
        context.register(key, TradeOfferBuilder.itemsForEmeralds(item, buyingCount, sellingCount, maxUses));
        if (addToTag) NORMAL_OVERLAY_OFFERS.add(key);
    }

    private static void special(BootstrapContext<TradeOffer> context, ItemLike item, int buyingCount, int sellingCount, int maxUses, boolean addToTag) {
        special(context, new ItemStack(item, sellingCount), buyingCount, sellingCount, maxUses, addToTag);
    }

    private static void special(BootstrapContext<TradeOffer> context, ItemStack stack, int buyingCount, int sellingCount, int maxUses, boolean addToTag) {
        var key = key("wandering_trader/special/buy_" + TradeOfferDatagen.itemPath(stack.getItem()));
        context.register(key, TradeOfferBuilder.itemsForEmeralds(stack, buyingCount, sellingCount, maxUses));
        if (addToTag) SPECIAL_OVERLAY_OFFERS.add(key);
    }
}
