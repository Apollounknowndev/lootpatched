package dev.worldgen.datapatched.api;

import com.mojang.serialization.MapCodec;
import dev.worldgen.datapatched.api.loot.LootModifier;
import dev.worldgen.datapatched.api.trade.TradeOffer;
import dev.worldgen.datapatched.impl.Datapatched;
import dev.worldgen.datapatched.impl.trade.provider.TradeOfferProvider;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public interface DatapatchedRegistries {
    ResourceKey<Registry<MapCodec<? extends LootModifier>>> LOOT_MODIFIER_TYPE = key("loot_modifier_type");
    ResourceKey<Registry<MapCodec<? extends TradeOffer>>> TRADE_OFFER_TYPE = key("trade_offer_type");
    ResourceKey<Registry<LootModifier>> LOOT_MODIFIER = key("loot_modifier");
    ResourceKey<Registry<TradeOffer>> TRADE_OFFER = key("trade_offer");
    ResourceKey<Registry<TradeOfferProvider>> TRADE_OFFER_PROVIDER = key("trade_offer_provider");

    private static <T> ResourceKey<Registry<T>> key(String name) {
        return ResourceKey.createRegistryKey(Datapatched.id(name));
    }
}