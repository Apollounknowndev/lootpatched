package dev.worldgen.datapatched.api;

import com.mojang.serialization.MapCodec;
import dev.worldgen.datapatched.api.loot.LootModifier;
import dev.worldgen.datapatched.api.trade.TradeOffer;
import net.minecraft.core.Registry;
import net.msrandom.multiplatform.annotations.Expect;

public class DatapatchedBuiltInRegistries {
    @Expect public static final Registry<MapCodec<? extends LootModifier>> LOOT_MODIFIER_TYPE;
    @Expect public static final Registry<MapCodec<? extends TradeOffer>> TRADE_OFFER_TYPE;
}
