package io.github.lunaprime.dragoninncobblemon.entity.client;

import io.github.lunaprime.dragoninncobblemon.DragonInnCobblemonMod;
import io.github.lunaprime.dragoninncobblemon.entity.custom.Wormhole;
import io.github.lunaprime.dragoninncobblemon.entity.custom.WormholeStatic;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class WormholeStaticRenderer extends MobEntityRenderer<WormholeStatic, WormholeStaticModel<WormholeStatic>> {
    public WormholeStaticRenderer(EntityRendererFactory.Context context) {
        super(context, new WormholeStaticModel<>(context.getPart(WormholeStaticModel.WORMHOLE_STATIC)), 0f);
    }

    @Override
    public Identifier getTexture(WormholeStatic entity) {
        return Identifier.of(DragonInnCobblemonMod.MOD_ID, "textures/entity/wormhole/wormhole.png");
    }

    @Override
    public void render(WormholeStatic livingEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.scale(0.5f,0.5f,0.5f);
        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
