package dev.worldgen.datapatched.impl.trade.offer;

import com.mojang.serialization.MapCodec;
import dev.worldgen.datapatched.api.trade.TradeOffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import net.minecraft.core.HolderSet;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.item.trading.MerchantOffer;

public record Group(HolderSet<TradeOffer> trades) implements TradeOffer {
    public static final MapCodec<Group> CODEC;

    public Group(HolderSet<TradeOffer> trades) {
        this.trades = trades;
    }

    public List<MerchantOffer> create(AbstractVillager merchant, RandomSource random) {
        List<MerchantOffer> offers = new ArrayList();
        Stream var10000 = this.trades.stream().map((entry) -> ((TradeOffer)entry.value()).create(merchant, random));
        Objects.requireNonNull(offers);
        var10000.forEach(offers::addAll);
        return offers;
    }

    public MapCodec<? extends TradeOffer> codec() {
        return CODEC;
    }

    public HolderSet<TradeOffer> trades() {
        return this.trades;
    }

    static {
        CODEC = TradeOffer.LIST_CODEC.fieldOf("trades").xmap(Group::new, Group::trades);
    }
}
