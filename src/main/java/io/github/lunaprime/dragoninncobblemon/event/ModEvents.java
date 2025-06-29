package io.github.lunaprime.dragoninncobblemon.event;

import io.github.lunaprime.dragoninncobblemon.DragonInnCobblemonMod;
import io.github.lunaprime.dragoninncobblemon.entity.ModEntities;
import io.github.lunaprime.dragoninncobblemon.event.events.WormholeSpawn;
import io.github.lunaprime.dragoninncobblemon.event.events.WormholeTeleport;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.dimension.DimensionType;

import java.lang.reflect.Array;
import java.util.UUID;

import static io.github.lunaprime.dragoninncobblemon.event.events.WormholeSpawn.WormholeSpawner;

public class ModEvents {

    private static double cooldown = 240;
    private static int randomInt = 0;
    private static String dimension;
    private static Random random = Random.create();

    public static void registerModEvents(){
        DragonInnCobblemonMod.LOGGER.info("Registering mod events for {}.", DragonInnCobblemonMod.MOD_ID);

        UseEntityCallback.EVENT.register(((player, world, hand, entity, hitResult) -> {
            DragonInnCobblemonMod.LOGGER.info("{}", entity.getName().getString());
            WormholeTeleport.Teleport(player, world, hand, entity);
            return ActionResult.PASS;
        }));

        ServerTickEvents.END_WORLD_TICK.register(world -> {
            if (cooldown == 0){
                //DragonInnCobblemonMod.LOGGER.info("Cooldown over");
                cooldown = 240;
                for (ServerPlayerEntity player : world.getPlayers()){
                    randomInt = random.nextBetween(1, 1500);
                    //DragonInnCobblemonMod.LOGGER.info("Random Number Generated: {}", randomInt);
                    if(randomInt == 1){
                        //DragonInnCobblemonMod.LOGGER.info("Random Number Passed");
                        dimension = player.getWorld().getDimensionEntry().getIdAsString();
                        if (dimension.equals("minecraft:overworld")){
                            WormholeSpawner(world, player);
                        }
                        else {
                            //DragonInnCobblemonMod.LOGGER.info("Player not in Overworld.");
                        }

                    }

                }
            }
            else {
                cooldown--;
            }
        });
    }





}
