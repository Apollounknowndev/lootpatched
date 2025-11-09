package dev.worldgen.datapatched.data.generator;

import dev.worldgen.datapatched.api.DatapatchedRegistries;
import dev.worldgen.datapatched.api.trade.TradeOffer;
import dev.worldgen.datapatched.data.DatapatchedTagsProvider;
import dev.worldgen.datapatched.impl.Datapatched;
import dev.worldgen.datapatched.impl.trade.provider.TradeOfferProvider;
import net.minecraft.core.HolderSet;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;

import java.util.List;

public class TradeOfferProviderDatagen {
    public static void bootstrap(BootstrapContext<TradeOfferProvider> context) {
        context.register(key("wandering_trader"), new TradeOfferProvider(List.of(
            new TradeOfferProvider.TradeTier(5, tag(context, "wandering_trader/normal")),
            new TradeOfferProvider.TradeTier(1, tag(context, "wandering_trader/special"))
        )));
    }

    private static HolderSet<TradeOffer> tag(BootstrapContext<TradeOfferProvider> context, String name) {
        return context.lookup(DatapatchedRegistries.TRADE_OFFER).get(DatapatchedTagsProvider.key(name)).get();
    }

    private static ResourceKey<TradeOfferProvider> key(String name) {
        return ResourceKey.create(DatapatchedRegistries.TRADE_OFFER_PROVIDER, Datapatched.id(name));
    }
}
