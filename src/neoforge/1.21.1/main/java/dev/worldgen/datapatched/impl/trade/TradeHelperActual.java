package dev.worldgen.datapatched.impl.trade;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.npc.VillagerData;
import net.msrandom.multiplatform.annotations.Actual;

public class TradeHelperActual {
    @Actual
    public static ResourceLocation getProfession(VillagerData data) {
        return ResourceLocation.parse(data.getProfession().toString());
    }

    @Actual
    public static ResourceLocation getType(VillagerData data) {
        return ResourceLocation.parse(data.getType().toString());
    }

    @Actual
    public static int getLevel(VillagerData data) {
        return data.getLevel();
    }
}
