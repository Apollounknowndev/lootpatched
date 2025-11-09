package dev.worldgen.datapatched.impl.trade.offer;

import com.mojang.serialization.MapCodec;
import dev.worldgen.datapatched.api.trade.TradeOffer;
import java.util.List;
import net.minecraft.core.HolderSet;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.item.trading.MerchantOffer;

public record Random(HolderSet<TradeOffer> trades) implements TradeOffer {
    public static final MapCodec<Random> CODEC;

    public Random(HolderSet<TradeOffer> trades) {
        this.trades = trades;
    }

    public List<MerchantOffer> create(AbstractVillager merchant, RandomSource random) {
        return (List)this.trades.getRandomElement(random).map((entry) -> ((TradeOffer)entry.value()).create(merchant, random)).orElse(List.of());
    }

    public MapCodec<? extends TradeOffer> codec() {
        return CODEC;
    }

    public HolderSet<TradeOffer> trades() {
        return this.trades;
    }

    static {
        CODEC = TradeOffer.LIST_CODEC.fieldOf("trades").xmap(Random::new, Random::trades);
    }
}
