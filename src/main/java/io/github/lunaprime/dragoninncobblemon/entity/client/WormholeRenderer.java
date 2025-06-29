package io.github.lunaprime.dragoninncobblemon.entity.client;

import io.github.lunaprime.dragoninncobblemon.DragonInnCobblemonMod;
import io.github.lunaprime.dragoninncobblemon.entity.custom.Wormhole;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class WormholeRenderer extends MobEntityRenderer<Wormhole, WormholeModel<Wormhole>> {
    public WormholeRenderer(EntityRendererFactory.Context context) {
        super(context, new WormholeModel<>(context.getPart(WormholeModel.WORMHOLE)), 0f);
    }

    @Override
    public Identifier getTexture(Wormhole entity) {
        return Identifier.of(DragonInnCobblemonMod.MOD_ID, "textures/entity/wormhole/wormhole.png");
    }

    @Override
    public void render(Wormhole livingEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.scale(0.5f,0.5f,0.5f);
        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
