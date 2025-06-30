package io.github.lunaprime.dragoninncobblemon.entity.custom;

import io.github.lunaprime.dragoninncobblemon.config.ConfigReader;
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
    private int lifespan = wormholeTicks();
    private int age = 0;

    public Wormhole(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
        this.setInvulnerable(true); //Not sure if this does anything, as players can still hit the entity
        this.noClip = true; //This disables collision, but not interactability.
    }

    //Grabs the despawn ticks value from a class that reads a config file, and if empty sets it to 7200 as a default.
    private static int wormholeTicks(){
        int ticks = Integer.parseInt(ConfigReader.configHashMap.get("WormholeDespawnTicks"));
        if (ticks == 0){
            ticks = 7200;
        }

        return ticks;
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
            this.discard(); //If the age (which goes up every tick) is longer than the set lifespan, it despawns the entity.
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
