package dev.worldgen.datapatched.impl.loot.modifier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.worldgen.datapatched.impl.Datapatched;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ExtraCodecs;

public record CommonData(Predicate<ResourceLocation> target, int priority) {
    private static final ResourceLocation UNKNOWN_ID = Datapatched.id("unknown");
    private static final Codec<Predicate<ResourceLocation>> TARGET_CODEC = Codec.withAlternative(
        ExtraCodecs.PATTERN.fieldOf("regex").codec().xmap(pattern -> table -> pattern.asPredicate().test(table.toString()), predicate -> Pattern.compile("")),
        Codec.withAlternative(
            ResourceLocation.CODEC.xmap(key -> key::equals, predicate -> UNKNOWN_ID),
            ResourceLocation.CODEC.listOf().xmap(list -> list::contains, predicate -> List.of(UNKNOWN_ID))
        )
    );

    public static MapCodec<CommonData> codec(int defaultPriority) {
        return RecordCodecBuilder.mapCodec(instance -> instance.group(
            TARGET_CODEC.fieldOf("targets").forGetter(CommonData::target),
            Codec.INT.fieldOf("priority").orElse(defaultPriority).forGetter(CommonData::priority)
        ).apply(instance, CommonData::new));
    }
}

