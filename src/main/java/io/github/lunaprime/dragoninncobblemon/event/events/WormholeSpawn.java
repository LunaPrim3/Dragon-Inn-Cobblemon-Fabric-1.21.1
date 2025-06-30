package io.github.lunaprime.dragoninncobblemon.event.events;

import io.github.lunaprime.dragoninncobblemon.DragonInnCobblemonMod;
import io.github.lunaprime.dragoninncobblemon.entity.ModEntities;
import io.github.lunaprime.dragoninncobblemon.entity.custom.Wormhole;
import io.github.lunaprime.dragoninncobblemon.net.Packets;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.SpawnHelper;

public class WormholeSpawn {

    public static void WormholeSpawner(ServerWorld serverWorld, ServerPlayerEntity player) {

        BlockPos pos = player.getBlockPos();
        PlayerManager playerManager = player.getServer().getPlayerManager();
        int x = (pos.getX() + (serverWorld.random.nextInt(32) + 16));
        int z = (pos.getZ() + (serverWorld.random.nextInt(32) + 16));
        int y = ((serverWorld.getTopY(Heightmap.Type.WORLD_SURFACE, x, z) + 64) + serverWorld.random.nextInt(24)); //Gets the top ground block
        DragonInnCobblemonMod.LOGGER.info("Wormhole Spawner Called, BlockPos = {} {} {}.", x, y, z);
        if (SpawnHelper.isClearForSpawn(serverWorld, pos, serverWorld.getBlockState(pos), serverWorld.getFluidState(pos), ModEntities.WORMHOLE)) {
            //DragonInnCobblemonMod.LOGGER.info("Passed spawn checks");
            Wormhole wormhole = new Wormhole(ModEntities.WORMHOLE, serverWorld);
            wormhole.refreshPositionAndAngles(x, y, z, 0F, 0F);
            serverWorld.spawnEntity(wormhole);
            //DragonInnCobblemonMod.LOGGER.info("Spawning Entity");
            String message = "A wormhole has opened near " + player.getNameForScoreboard() + "!";
            playerManager.broadcast(Text.literal(message).formatted(Formatting.DARK_PURPLE), false);
            for (ServerPlayerEntity serverPlayerEntity : playerManager.getPlayerList()){
                serverPlayerEntity.playSoundToPlayer(SoundEvents.BLOCK_END_PORTAL_SPAWN, SoundCategory.NEUTRAL, 1, 1);
            }
        }

    }
}
