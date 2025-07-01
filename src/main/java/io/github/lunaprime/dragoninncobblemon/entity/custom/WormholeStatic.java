package io.github.lunaprime.dragoninncobblemon.entity.custom;

import io.github.lunaprime.dragoninncobblemon.DragonInnCobblemonMod;
import io.github.lunaprime.dragoninncobblemon.config.ConfigReader;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class WormholeStatic extends AnimalEntity {
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    private int age = 0;
    private static double staticX = 0;
    private static double staticY = 120;
    private static double staticZ = 0;

    public WormholeStatic(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
        this.setInvulnerable(true);
        this.noClip = true;
    }
    private static void configReadPosition(){
        if (FabricLoader.getInstance().getEnvironmentType().equals(EnvType.SERVER)){
            DragonInnCobblemonMod.LOGGER.info("Reading config for Wormhole Position -------------------------------");
            staticX = ConfigReader.configHashMap.get("WormholeStaticPositionX");
            staticY = ConfigReader.configHashMap.get("WormholeStaticPositionY");
            staticZ = ConfigReader.configHashMap.get("WormholeStaticPositionZ");
        }
    }

    /*@Override
    protected void initGoals(){

    }*/

    public static DefaultAttributeContainer.Builder createAttributes(){
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 9999999)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0)
                .add(EntityAttributes.GENERIC_ARMOR, 9999999)
                .add(EntityAttributes.GENERIC_GRAVITY, 0)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 0)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 0)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 999)
                .add(EntityAttributes.GENERIC_EXPLOSION_KNOCKBACK_RESISTANCE, 999);
    }

    private void setupAnimationStates(){
        /*if (this.idleAnimationTimeout <= 0){
            this.idleAnimationTimeout = 60;
            this.idleAnimationState.start(this.age);
        } else {
            --this.idleAnimationTimeout;
        }*/
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 1;
            this.idleAnimationState.start(this.age);
        }
    }

    @Override
    public void tick(){
        super.tick();

        age++;

        if(this.getWorld().isClient()){
            this.setupAnimationStates();
        }
        else {
            this.setPos(staticX,staticY,staticZ);
        }
    }

    @Override
    public boolean isBreedingItem(ItemStack itemStack) {
        return false;
    }

    @Override
    public @Nullable PassiveEntity createChild(ServerWorld serverWorld, PassiveEntity passiveEntity) {
        return null;
    }
}
