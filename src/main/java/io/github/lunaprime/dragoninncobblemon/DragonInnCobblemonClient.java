package io.github.lunaprime.dragoninncobblemon;

import io.github.lunaprime.dragoninncobblemon.entity.ModEntities;
import io.github.lunaprime.dragoninncobblemon.entity.client.WormholeModel;
import io.github.lunaprime.dragoninncobblemon.entity.client.WormholeRenderer;
import io.github.lunaprime.dragoninncobblemon.entity.client.WormholeStaticModel;
import io.github.lunaprime.dragoninncobblemon.entity.client.WormholeStaticRenderer;
import io.github.lunaprime.dragoninncobblemon.net.Packets;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class DragonInnCobblemonClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        EntityModelLayerRegistry.registerModelLayer(WormholeModel.WORMHOLE, WormholeModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.WORMHOLE, WormholeRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(WormholeStaticModel.WORMHOLE_STATIC, WormholeStaticModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.WORMHOLE_STATIC, WormholeStaticRenderer::new);

        ClientPlayNetworking.registerGlobalReceiver(Packets.chatMessage.PACKET_ID, (((payload, context) -> {
            ClientPlayerEntity player = context.client().player;
            if (player == null){
                return;
            }
            context.client().inGameHud.getChatHud().addMessage(Text.literal(payload.message()).formatted(Formatting.DARK_PURPLE, Formatting.BOLD));
            DragonInnCobblemonMod.LOGGER.info("DragonInnCobblemonClient: Message packet received {}.", payload.message());
        })));

    }
}
