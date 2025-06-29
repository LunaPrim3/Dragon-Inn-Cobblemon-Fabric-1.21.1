package io.github.lunaprime.dragoninncobblemon.entity.custom;

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class Wormhole extends AnimalEntity {
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    private int lifespan = 2400; //At 20TPS, this equals two minutes
    private int age = 0;

    public Wormhole(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
        this.setInvulnerable(true);
        this.noClip = true;
    }

    /*@Override
    protected void initGoals(){

    }*/

    public static DefaultAttributeContainer.Builder createAttributes(){
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 999)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0)
                .add(EntityAttributes.GENERIC_ARMOR, 999)
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
        if(age > lifespan){
            this.discard();
        }
    }

    public void stopMovement() {
        this.getNavigation().stop();
        this.setSidewaysSpeed(0.0F);
        this.setUpwardSpeed(0.0F);
        this.setMovementSpeed(0.0F);
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
