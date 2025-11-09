package dev.worldgen.datapatched.impl.trade.offer;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.worldgen.datapatched.api.trade.TradeOffer;
import dev.worldgen.datapatched.impl.trade.TradeHelper;
import java.util.List;
import java.util.Map;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.npc.VillagerDataHolder;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.item.trading.MerchantOffer;

public record TypeSpecific(Map<ResourceKey<VillagerType>, Holder<TradeOffer>> trades, Holder<TradeOffer> fallback) implements TradeOffer {
    public static final MapCodec<TypeSpecific> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(Codec.unboundedMap(ResourceKey.codec(Registries.VILLAGER_TYPE), TradeOffer.ENTRY_CODEC).fieldOf("trades").forGetter(TypeSpecific::trades), TradeOffer.ENTRY_CODEC.fieldOf("fallback").orElse(EMPTY).forGetter(TypeSpecific::fallback)).apply(instance, TypeSpecific::new));

    public TypeSpecific(Map<ResourceKey<VillagerType>, Holder<TradeOffer>> trades, Holder<TradeOffer> fallback) {
        this.trades = trades;
        this.fallback = fallback;
    }

    public List<MerchantOffer> create(AbstractVillager merchant, RandomSource random) {
        if (merchant instanceof VillagerDataHolder container) {
            ResourceKey<VillagerType> key = ResourceKey.create(Registries.VILLAGER_TYPE, TradeHelper.getType(container.getVillagerData()));
            return ((TradeOffer)((Holder)this.trades.getOrDefault(key, this.fallback)).value()).create(merchant, random);
        } else {
            return ((TradeOffer)this.fallback.value()).create(merchant, random);
        }
    }

    public MapCodec<? extends TradeOffer> codec() {
        return CODEC;
    }

    public Map<ResourceKey<VillagerType>, Holder<TradeOffer>> trades() {
        return this.trades;
    }

    public Holder<TradeOffer> fallback() {
        return this.fallback;
    }
}
