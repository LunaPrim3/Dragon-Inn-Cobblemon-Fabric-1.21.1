package io.github.lunaprime.dragoninncobblemon.entity;

import io.github.lunaprime.dragoninncobblemon.DragonInnCobblemonMod;
import io.github.lunaprime.dragoninncobblemon.entity.custom.Wormhole;
import io.github.lunaprime.dragoninncobblemon.entity.custom.WormholeStatic;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {

    public static final EntityType<Wormhole> WORMHOLE = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(DragonInnCobblemonMod.MOD_ID, "wormhole"),
            EntityType.Builder.create(Wormhole::new, SpawnGroup.CREATURE)
                    .makeFireImmune()
                    .dimensions(3.5f, 3.5f).build());

    public static final EntityType<WormholeStatic> WORMHOLE_STATIC = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(DragonInnCobblemonMod.MOD_ID, "wormhole_static"),
            EntityType.Builder.create(WormholeStatic::new, SpawnGroup.CREATURE)
                    .makeFireImmune()
                    .dimensions(3.5f, 3.5f).build());

    public static void registerModEntities(){
        DragonInnCobblemonMod.LOGGER.info("Registering Entities for {}", DragonInnCobblemonMod.MOD_ID);
    }
}
