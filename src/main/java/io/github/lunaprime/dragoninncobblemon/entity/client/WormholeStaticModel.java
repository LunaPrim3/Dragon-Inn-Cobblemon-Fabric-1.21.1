package io.github.lunaprime.dragoninncobblemon.entity.client;

import io.github.lunaprime.dragoninncobblemon.DragonInnCobblemonMod;
import io.github.lunaprime.dragoninncobblemon.entity.custom.Wormhole;
import io.github.lunaprime.dragoninncobblemon.entity.custom.WormholeStatic;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class WormholeStaticModel<T extends WormholeStatic> extends SinglePartEntityModel<T> {
    public static final EntityModelLayer WORMHOLE_STATIC = new EntityModelLayer(Identifier.of(DragonInnCobblemonMod.MOD_ID, "wormhole_static"), "main");
    private final ModelPart root;
    private final ModelPart bone;
    public WormholeStaticModel(ModelPart root) {
        this.root = root.getChild("root");
        this.bone = this.root.getChild("bone");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.pivot(-32.0F, -8.0F, 0.0F));

        ModelPartData bone = root.addChild("bone", ModelPartBuilder.create().uv(0, 0).cuboid(-32.0F, -1.0F, -32.0F, 64.0F, 1.0F, 64.0F, new Dilation(0.0F)), ModelTransform.of(32.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 256, 256);
    }
    @Override
    public void setAngles(WormholeStatic entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);

        this.updateAnimation(entity.idleAnimationState, WormholeAnimations.WORMHOLE_IDLE, ageInTicks, 1f);
    }
    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int colour) {
        root.render(matrices, vertexConsumer, light, overlay, colour);
    }
    @Override
    public ModelPart getPart() {
        return root;
    }
    /*
    private final ModelPart bone;
    private final ModelPart root;
    public WormholeModel(ModelPart root) {
        this.root = root.getChild("root");
        this.bone = this.root.getChild("bone");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData bone = root.addChild("bone", ModelPartBuilder.create().uv(0, 126).cuboid(-64.0F, -2.0F, -65.0F, 128.0F, 2.0F, 128.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 256, 256);
    }



    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int colour) {
        root.render(matrices, vertexConsumer, light, overlay, colour);
    }*/
}
