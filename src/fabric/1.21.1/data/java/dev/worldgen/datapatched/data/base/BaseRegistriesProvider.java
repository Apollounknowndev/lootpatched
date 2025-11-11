package dev.worldgen.datapatched.data.base;

import dev.worldgen.datapatched.api.DatapatchedRegistries;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class BaseRegistriesProvider extends FabricDynamicRegistryProvider {
    public BaseRegistriesProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider provider, Entries entries) {
        entries.addAll(provider.lookupOrThrow(DatapatchedRegistries.TRADE_OFFER));
        entries.addAll(provider.lookupOrThrow(DatapatchedRegistries.TRADE_OFFER_PROVIDER));
    }

    @Override
    public String getName() {
        return "Base registries";
    }
}
