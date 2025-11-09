package dev.worldgen.datapatched.mixin.trade;

import dev.worldgen.datapatched.impl.trade.TradeHelper;
import dev.worldgen.datapatched.impl.trade.provider.TradeOfferProvider;
import java.util.Optional;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerData;
import net.minecraft.world.entity.npc.VillagerProfession;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Villager.class)
public abstract class VillagerMixin {
    @Inject(
        at = {@At("HEAD")},
        method = {"updateTrades"},
        cancellable = true
    )
    private void sellcraft$injectSellcraftTrades(CallbackInfo ci) {
        Villager $this = (Villager) (Object) this;
        VillagerData data = $this.getVillagerData();
        ResourceLocation id = TradeHelper.getProfession(data);
        if (id.getNamespace().equals("minecraft")) {
            id = ResourceLocation.fromNamespaceAndPath("datapatched", id.getPath());
        }

        ResourceKey<VillagerProfession> profession = ResourceKey.create(Registries.VILLAGER_PROFESSION, id);
        Optional<TradeOfferProvider> tradeProvider = TradeOfferProvider.getProvider($this.registryAccess(), profession.location());
        int level = TradeHelper.getLevel(data);
        if (tradeProvider.isPresent() && level <= tradeProvider.get().tiers().size()) {
            TradeOfferProvider.TradeTier tradeTier = tradeProvider.get().tiers().get(level - 1);
            TradeHelper.addDatapatchedTrades($this, $this.getOffers(), tradeTier);
            ci.cancel();
        }

    }
}