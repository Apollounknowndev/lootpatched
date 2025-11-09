package dev.worldgen.datapatched.data;

import dev.worldgen.datapatched.api.DatapatchedRegistries;
import dev.worldgen.datapatched.data.generator.TradeOfferDatagen;
import dev.worldgen.datapatched.data.generator.TradeOfferProviderDatagen;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;

public class DatapatchedDatagen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        FabricDataGenerator.Pack pack = generator.createPack();
        pack.addProvider(DatapatchedRegistriesProvider::new);
        pack.addProvider(DatapatchedTagsProvider::new);
    }

    @Override
    public void buildRegistry(RegistrySetBuilder builder) {
        builder.add(DatapatchedRegistries.TRADE_OFFER, TradeOfferDatagen::bootstrap);
        builder.add(DatapatchedRegistries.TRADE_OFFER_PROVIDER, TradeOfferProviderDatagen::bootstrap);
    }
}
