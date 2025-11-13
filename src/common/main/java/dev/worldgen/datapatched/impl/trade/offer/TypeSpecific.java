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

public record TypeSpecific(List<ResourceKey<VillagerType>> types, Holder<TradeOffer> trade, Holder<TradeOffer> fallback) implements TradeOffer {
    private static final Codec<ResourceKey<VillagerType>> TYPE_CODEC = ResourceKey.codec(Registries.VILLAGER_TYPE);
    public static final MapCodec<TypeSpecific> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
        Codec.withAlternative(TYPE_CODEC.listOf(), TYPE_CODEC, List::of).fieldOf("types").forGetter(TypeSpecific::types),
        TradeOffer.ENTRY_CODEC.fieldOf("trade").forGetter(TypeSpecific::trade),
        TradeOffer.ENTRY_CODEC.fieldOf("fallback").orElse(EMPTY).forGetter(TypeSpecific::fallback)
    ).apply(instance, TypeSpecific::new));

    public List<MerchantOffer> create(AbstractVillager merchant, RandomSource random) {
        if (merchant instanceof VillagerDataHolder container) {
            ResourceKey<VillagerType> key = ResourceKey.create(Registries.VILLAGER_TYPE, TradeHelper.getType(container.getVillagerData()));
            if (this.types.contains(key)) {
                return this.trade.value().create(merchant, random);
            }
        }
        return this.fallback.value().create(merchant, random);
    }

    public MapCodec<? extends TradeOffer> codec() {
        return CODEC;
    }
}
