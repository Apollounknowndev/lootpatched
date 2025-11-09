package dev.worldgen.datapatched.data;

import dev.worldgen.datapatched.api.DatapatchedRegistries;
import dev.worldgen.datapatched.api.trade.TradeOffer;
import dev.worldgen.datapatched.data.generator.offer.WanderingTraderOffers;
import dev.worldgen.datapatched.impl.Datapatched;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.TagKey;

import java.util.concurrent.CompletableFuture;

public class DatapatchedTagsProvider extends FabricTagProvider<TradeOffer> {
    public DatapatchedTagsProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, DatapatchedRegistries.TRADE_OFFER, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        builder(key("wandering_trader/normal_overlay")).addAll(WanderingTraderOffers.NORMAL_OVERLAY_OFFERS);
        builder(key("wandering_trader/special_overlay")).addAll(WanderingTraderOffers.SPECIAL_OVERLAY_OFFERS);
    }

    public static TagKey<TradeOffer> key(String name) {
        return TagKey.create(DatapatchedRegistries.TRADE_OFFER, Datapatched.id(name));
    }
}
