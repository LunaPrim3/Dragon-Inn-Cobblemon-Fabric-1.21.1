package io.github.lunaprime.dragoninncobblemon.config;
import io.github.lunaprime.dragoninncobblemon.DragonInnCobblemonMod;
import net.fabricmc.loader.api.FabricLoader;
import java.io.*;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Scanner;


public class ConfigReader {

    public static int wormholeDespawnTicks = WormholeDespawnTicks();
    private static Path configDirectory(){
        return FabricLoader.getInstance().getConfigDir();
    }

    public static void registerConfigReader(){
        DragonInnCobblemonMod.LOGGER.info("Preparing mod config for " + DragonInnCobblemonMod.MOD_ID);


        try{
            File config = new File(configDirectory() + "\\dragoninncobblemon.json");
            if (config.createNewFile()){
                DragonInnCobblemonMod.LOGGER.info("Creating new configuration file for " + DragonInnCobblemonMod.MOD_ID);
            } else{
                DragonInnCobblemonMod.LOGGER.info("Dragon Inn Cobblemon configuration file already exists, skipping step");
                DragonInnCobblemonMod.LOGGER.info("Dragon Inn Cobblemon: Wormhole Despawn Ticks: {}", wormholeDespawnTicks);
            }
        }
        catch (IOException e){
            DragonInnCobblemonMod.LOGGER.info("An error occurred when creating the Dragon Inn Cobblemon configuration file.");
        }

    }




    public static int WormholeDespawnTicks(){ //Reads the configuration file and returns the integer value set
        int ticks = 7200; //2400 ticks is roughly 2 minutes, defaults to 6 mins if nothing is read.
        File config = new File(configDirectory() + "\\dragoninncobblemon.json");
        try(Scanner readConfig = new Scanner(config)){
            while ((readConfig.hasNextLine())){
                String[] keyValuePair = readConfig.nextLine().split(",",2);
                ticks = Integer.parseInt(keyValuePair[1]);
            }
        }
        catch (Exception e ){
            DragonInnCobblemonMod.LOGGER.info("An error occurred while attempting to read the Dragon Inn Cobblemon configuration file: "+ e);
        }

        return ticks;
    }



}
