package io.github.lunaprime.dragoninncobblemon;


import io.github.lunaprime.dragoninncobblemon.entity.ModEntities;
import io.github.lunaprime.dragoninncobblemon.entity.custom.Wormhole;
import io.github.lunaprime.dragoninncobblemon.entity.custom.WormholeStatic;
import io.github.lunaprime.dragoninncobblemon.event.ModEvents;
import io.github.lunaprime.dragoninncobblemon.net.Packets;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DragonInnCobblemonMod implements ModInitializer {

    public static final String MOD_ID = "dragoninncobblemon";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    @Override
    public void onInitialize() {

        ModEntities.registerModEntities();
        ModEvents.registerModEvents();

        FabricDefaultAttributeRegistry.register(ModEntities.WORMHOLE, Wormhole.createAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.WORMHOLE_STATIC, WormholeStatic.createAttributes());
        PayloadTypeRegistry.playS2C().register(Packets.chatMessage.PACKET_ID, Packets.chatMessage.CODEC);

    }
}
