package io.github.lunaprime.dragoninncobblemon.event.events;

import io.github.lunaprime.dragoninncobblemon.DragonInnCobblemonMod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;

import java.util.UUID;

public class WormholeTeleport {

    public static void Teleport(PlayerEntity player, World world, Hand hand, Entity entity){
        String name = entity.getName().getString();
        Vec3d vec3d = new Vec3d(-144, 74, -139);
        Vec3d emptyVec3d = new Vec3d(0,0,0);
        TeleportTarget teleportTarget;
        TeleportTarget.PostDimensionTransition postDimensionTransition;
        UUID uuid = player.getUuid();
        MinecraftServer server = world.getServer();

        if (!world.isClient && !player.isSpectator() && hand.name().equals("MAIN_HAND") && (name.equals("Wormhole") || name.equals("Static Wormhole"))){
            ServerPlayerEntity serverPlayerEntity = serverPlayerEntity(world, uuid);
            //DragonInnCobblemonMod.LOGGER.info("Entity is {}, hand = {}. worlds = {}.", name, hand, world.getDimensionEntry().getIdAsString());
            postDimensionTransition = TeleportTarget.SEND_TRAVEL_THROUGH_PORTAL_PACKET.then(TeleportTarget.ADD_PORTAL_CHUNK_TICKET);
            ServerWorld ultraSpace = ultraSpace(server, world);

            if ((world.getDimensionEntry().getIdAsString().equals("ultra_space:custom_dimension_type"))){ //If player is in Ultra Space already, teleports them to the overworld
                if (server != null){
                    //DragonInnCobblemonMod.LOGGER.info("Teleporting to Overworld");
                    teleportTarget = new TeleportTarget(server.getOverworld(), serverPlayerEntity, postDimensionTransition);
                    player.teleportTo(teleportTarget);
                }
                else {
                    //DragonInnCobblemonMod.LOGGER.info("Server is Null");
                }
            }
            else { //If player isn't in Ultra Space, teleports them to Ultra Space.
                if (ultraSpace != null){
                    teleportTarget = new TeleportTarget(ultraSpace, vec3d, emptyVec3d, 0, 0, postDimensionTransition);
                    player.teleportTo(teleportTarget);
                }
                //else DragonInnCobblemonMod.LOGGER.info("Destination world is null, is ClownMod installed?");
            }

        }
    }

    private static ServerPlayerEntity serverPlayerEntity(World world, UUID uuid){
        MinecraftServer server = world.getServer();
        assert server != null;
        ServerPlayerEntity serverPlayerEntity = server.getPlayerManager().getPlayer(uuid);
        assert serverPlayerEntity != null;

        //Object[] array = server.getWorldRegistryKeys().toArray();

        return serverPlayerEntity;
    }

    private static ServerWorld ultraSpace(MinecraftServer server, World world){
        RegistryKey<World> usKey = RegistryKey.of(RegistryKeys.WORLD, Identifier.of("ultra_space:custom_dimension"));

        if (server != null) {
            return server.getWorld(usKey);
        }
        else return null;
    }


}
